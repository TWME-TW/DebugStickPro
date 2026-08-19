import assert from 'node:assert/strict'
import mineflayer from 'mineflayer'

const port = Number(process.env.DSP_E2E_PORT)
const version = process.env.DSP_MINEFLAYER_VERSION
const username = process.env.DSP_E2E_USERNAME ?? 'DSPTest'
const timeoutMs = 45_000

if (!Number.isInteger(port) || !version) {
  throw new Error('DSP_E2E_PORT and DSP_MINEFLAYER_VERSION are required')
}

const bot = mineflayer.createBot({
  host: '127.0.0.1',
  port,
  username,
  version,
  auth: 'offline'
})

const messages = []
const entityPackets = []
bot.on('messagestr', message => messages.push(message))
bot._client.on('packet', (data, metadata) => {
  if (metadata.name.includes('spawn_entity') || metadata.name === 'entity_metadata' || metadata.name === 'entity_destroy') {
    entityPackets.push({
      name: metadata.name,
      entityId: data?.entityId,
      entityIds: data?.entityIds,
      type: data?.type
    })
  }
})

function waitFor(predicate, description, timeout = timeoutMs) {
  return new Promise((resolve, reject) => {
    const started = Date.now()
    const timer = setInterval(() => {
      try {
        const result = predicate()
        if (result) {
          clearInterval(timer)
          resolve(result)
        } else if (Date.now() - started >= timeout) {
          clearInterval(timer)
          reject(new Error(
            `Timed out waiting for ${description}. Messages: ${messages.join(' | ')}. ` +
            `Recent entity packets: ${JSON.stringify(entityPackets.slice(-80))}`
          ))
        }
      } catch (error) {
        clearInterval(timer)
        reject(error)
      }
    }, 50)
  })
}

async function rightClickBlock(target) {
  const swingArm = bot.swingArm
  bot.swingArm = () => {}
  try {
    const westFace = target.position.offset(-1, 0, 0).minus(target.position)
    const westFaceCenter = target.position.offset(0, 0.5, 0.5).minus(target.position)
    await bot.activateBlock(target, westFace, westFaceCenter)
  } finally {
    bot.swingArm = swingArm
  }
}

async function exerciseFreezeLifecycle(target, expectedBlockName) {
  const packetOffset = entityPackets.length
  await rightClickBlock(target)

  const displays = await waitFor(() => {
    const packets = entityPackets.slice(packetOffset)
    const itemDisplay = packets.find(packet =>
      packet.name.includes('spawn_entity') && packet.type === bot.registry.entitiesByName.item_display.id
    )
    const blockDisplay = packets.find(packet =>
      packet.name.includes('spawn_entity') && packet.type === bot.registry.entitiesByName.block_display.id
    )
    return itemDisplay && blockDisplay ? { itemDisplay, blockDisplay } : null
  }, 'VirtualEntities Item Display and Block Display')

  await bot.waitForTicks(5)
  await bot.lookAt(target.position.offset(0.5, 0.5, 0.5), true)
  await rightClickBlock(target)

  await waitFor(
    () => {
      const destroyPackets = entityPackets.slice(packetOffset)
        .filter(packet => packet.name === 'entity_destroy')
      const removed = entityId => destroyPackets.some(packet =>
        Array.from(packet.entityIds ?? []).includes(entityId)
      )
      return removed(displays.itemDisplay.entityId) && removed(displays.blockDisplay.entityId)
    },
    'VirtualEntities removal'
  )

  await bot.waitForTicks(10)
  const displayEntityIds = new Set(entityPackets.slice(packetOffset)
    .filter(packet =>
      packet.name.includes('spawn_entity') &&
      (packet.type === bot.registry.entitiesByName.item_display.id ||
        packet.type === bot.registry.entitiesByName.block_display.id)
    )
    .map(packet => packet.entityId))
  assert.equal(displayEntityIds.size, 2, 'unfreezing must not freeze a block behind the target')
  await waitFor(
    () => bot.blockAt(target.position)?.name === expectedBlockName,
    `${expectedBlockName} block restoration`
  )
}

try {
  await new Promise((resolve, reject) => {
    const timeout = setTimeout(() => reject(new Error('Timed out waiting for Mineflayer spawn')), timeoutMs)
    bot.once('spawn', () => {
      clearTimeout(timeout)
      resolve()
    })
    bot.once('error', reject)
    bot.once('kicked', reason => reject(new Error(`Mineflayer was kicked: ${reason}`)))
  })

  bot.setSettings({ locale: 'zh_TW' })
  await bot.waitForTicks(10)

  bot.chat('/dsp give')
  const debugStick = await waitFor(
    () => bot.inventory.items().find(item => item.name === 'blaze_rod'),
    'DebugStickPro item'
  )
  await waitFor(
    () => messages.some(message => message.includes('已將除錯棒給予')),
    'Traditional Chinese locale response'
  )
  await bot.equip(debugStick, 'hand')
  await bot.waitForTicks(20)

  bot.chat('/dsp mode freeze')
  await waitFor(
    () => messages.some(message => /freeze|凍結/i.test(message)),
    'freeze mode confirmation'
  )
  await bot.waitForTicks(10)

  const targetPosition = bot.entity.position.floored().offset(1, 0, 0)
  bot.chat(`/setblock ${targetPosition.x} ${targetPosition.y} ${targetPosition.z} minecraft:stone`)
  const target = await waitFor(() => {
    const block = bot.blockAt(targetPosition)
    return block?.name === 'stone' ? block : null
  }, 'integration-test stone block')
  assert.ok(target)
  await bot.waitForTicks(10)
  await bot.lookAt(target.position.offset(0.5, 0.5, 0.5), true)

  await exerciseFreezeLifecycle(target, 'stone')

  bot.chat(`/setblock ${targetPosition.x} ${targetPosition.y} ${targetPosition.z} minecraft:barrier`)
  const barrier = await waitFor(() => {
    const block = bot.blockAt(targetPosition)
    return block?.name === 'barrier' ? block : null
  }, 'integration-test barrier block')
  await bot.waitForTicks(10)
  await bot.lookAt(barrier.position.offset(0.5, 0.5, 0.5), true)
  await exerciseFreezeLifecycle(barrier, 'barrier')

  console.log(JSON.stringify({
    version,
    locale: 'zh_TW',
    command: true,
    miniMessageItem: debugStick.customName?.toString() ?? debugStick.displayName,
    virtualEntities: ['item_display', 'block_display'],
    removal: true,
    barrierRestoration: true
  }))
} finally {
  bot.quit('DebugStickPro E2E complete')
}
