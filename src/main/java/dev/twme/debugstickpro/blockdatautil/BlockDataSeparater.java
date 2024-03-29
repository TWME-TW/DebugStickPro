package dev.twme.debugstickpro.blockdatautil;

import dev.twme.debugstickpro.blockdatautil.subdata.*;
import dev.twme.debugstickpro.config.ConfigFile;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.*;
import org.bukkit.block.data.type.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class BlockDataSeparater {

    // 快取
    private static final HashMap<Material, ArrayList<SubBlockData>> cache = new HashMap<>();

    // 分解 BlockData 成 SubBlockData
    // separate BlockData into SubBlockData
    public static ArrayList<SubBlockData> separate(Block block) {
        return separate(block.getBlockData());
    }

    // 清除快取
    public static void clearCache() {
        cache.clear();
    }

    // 確認該材料是否有效
    private static boolean isValidMaterial(String material) {
        if (material == null) {
            return false;
        } else {
            try {
                Material.valueOf(material);
                return true;
            } catch (IllegalArgumentException var3) {
                return false;
            }
        }
    }

    // filter SubBlockData
    private static ArrayList<SubBlockData> filterSubBlockData(ArrayList<SubBlockData> blockDataList) {

        if (ConfigFile.BlockDataFilter.Whitelist.Enabled && !ConfigFile.BlockDataFilter.Whitelist.Whitelist.contains("*")) {
            blockDataList.removeIf(subBlockData -> !ConfigFile.BlockDataFilter.Whitelist.Whitelist.contains(subBlockData.name()));
        }

        if (ConfigFile.BlockDataFilter.Blacklist.Enabled || ConfigFile.BlockDataFilter.Blacklist.Blacklist.contains("*")) {
            blockDataList.removeIf(subBlockData -> ConfigFile.BlockDataFilter.Blacklist.Blacklist.contains(subBlockData.name()));
        }

        return blockDataList;
    }

    // 分解 BlockData 成 SubBlockData
    // separate BlockData into SubBlockData
    public static ArrayList<SubBlockData> separate(BlockData blockData) {


        ArrayList<SubBlockData> blockDataList = new ArrayList<>();

        if (blockData == null) {
            return blockDataList;
        }

        // when cached, use it
        if (cache.containsKey(blockData.getMaterial())) {

            for (SubBlockData subBlockData : cache.get(blockData.getMaterial())) {
                blockDataList.add(subBlockData.getDataFac(blockData));
            }
            return blockDataList;
        }

        // start separate !!

        if (blockData instanceof Ageable) {
            SubBlockData ageableUtil = new AgeableData(blockData);
            blockDataList.add(ageableUtil);
        }

        /* NOTE: Not Used
        if (blockData instanceof AmethystCluster) {
            Log.info("AmethystCluster");
        }
        */

        if (blockData instanceof AnaloguePowerable) {
            SubBlockData analoguePowerableData = new AnaloguePowerableData(blockData);
            blockDataList.add(analoguePowerableData);
        }

        if (blockData instanceof Attachable) {
            SubBlockData attachableData = new AttachableData(blockData);
            blockDataList.add(attachableData);
        }

        if (blockData instanceof Bamboo) {
            SubBlockData bambooData = new BambooData(blockData);
            blockDataList.add(bambooData);
        }

        /* NOTE: Not Used
        if (blockData instanceof Barrel) {
            Log.info("Barrel");
        }
        */

        if (blockData instanceof Bed) {
            SubBlockData bedData = new BedData(blockData);
            blockDataList.add(bedData);
        }

        if (blockData instanceof Beehive) {
            SubBlockData beehiveData = new BeehiveData(blockData);
            blockDataList.add(beehiveData);
        }
        if (blockData instanceof Bell) {
            SubBlockData bellData = new BellData(blockData);
            blockDataList.add(bellData);
        }
        if (blockData instanceof BigDripleaf) {
            SubBlockData bigDripleafData = new BigDripleafData(blockData);
            blockDataList.add(bigDripleafData);
        }

        if (blockData instanceof Bisected) {
            SubBlockData bisectedData = new BisectedData(blockData);
            blockDataList.add(bisectedData);
        }

        if (blockData instanceof BrewingStand) {
            SubBlockData brewingStandBottle_0 = new BrewingStandBottle_0(blockData);
            SubBlockData brewingStandBottle_1 = new BrewingStandBottle_1(blockData);
            SubBlockData brewingStandBottle_2 = new BrewingStandBottle_2(blockData);
            blockDataList.add(brewingStandBottle_0);
            blockDataList.add(brewingStandBottle_1);
            blockDataList.add(brewingStandBottle_2);
        }

        if (isValidMaterial("suspicious_sand")) {
            if (blockData instanceof Brushable) {
                SubBlockData brushable = new BrushableData(blockData);
                blockDataList.add(brushable);
            }
        }
        if (blockData instanceof BubbleColumn) {
            SubBlockData BubbleColumnData = new BubbleColumnData(blockData);
            blockDataList.add(BubbleColumnData);
        }

        if (blockData instanceof Cake) {
            SubBlockData cake = new CakeData(blockData);
            blockDataList.add(cake);
        }

        /* NOTE: Not Used
        if (blockData instanceof CalibratedSculkSensor) {
            Log.info("CalibratedSculkSensor");
        }
        */

        if (blockData instanceof Campfire) {
            SubBlockData campfire = new CampfireData(blockData);
            blockDataList.add(campfire);
        }

        if (blockData instanceof Candle) {
            SubBlockData candle = new CandleData(blockData);
            blockDataList.add(candle);
        }
        /* NOTE: Not Used
        if (blockData instanceof CaveVines) {
            Log.info("CaveVines");
        }
        */
        if (blockData instanceof CaveVinesPlant) {
            SubBlockData caveVinesPlant = new CaveVinesPlantData(blockData);
            blockDataList.add(caveVinesPlant);
        }
        /* NOTE: Not Used
        if (blockData instanceof Chain) {

        }
        */
        if (blockData instanceof Chest) {
            SubBlockData chest = new ChestData(blockData);
            blockDataList.add(chest);
        }

        if (blockData instanceof ChiseledBookshelf) {
            SubBlockData chiseledBookshelfSlot_0 = new ChiseledBookshelfSlot_0(blockData);
            SubBlockData chiseledBookshelfSlot_1 = new ChiseledBookshelfSlot_1(blockData);
            SubBlockData chiseledBookshelfSlot_2 = new ChiseledBookshelfSlot_2(blockData);
            SubBlockData chiseledBookshelfSlot_3 = new ChiseledBookshelfSlot_3(blockData);
            SubBlockData chiseledBookshelfSlot_4 = new ChiseledBookshelfSlot_4(blockData);
            SubBlockData chiseledBookshelfSlot_5 = new ChiseledBookshelfSlot_5(blockData);
            blockDataList.add(chiseledBookshelfSlot_0);
            blockDataList.add(chiseledBookshelfSlot_1);
            blockDataList.add(chiseledBookshelfSlot_2);
            blockDataList.add(chiseledBookshelfSlot_3);
            blockDataList.add(chiseledBookshelfSlot_4);
            blockDataList.add(chiseledBookshelfSlot_5);
        }
        /* NOTE: Not Used
        if (blockData instanceof Cocoa) {

        }
        */
        if (blockData instanceof CommandBlock) {
            SubBlockData commandBlockConditional = new CommandBlockData(blockData);
            blockDataList.add(commandBlockConditional);
        }

        if (blockData instanceof Comparator) {
            SubBlockData comparatorMode = new ComparatorData(blockData);
            blockDataList.add(comparatorMode);
        }
        /* NOTE: Not Used
        if (blockData instanceof CopperBulb) {

        }
        */
        /* NOTE: Not Used
        if (blockData instanceof CoralWallFan) {

        }
        */
        if (isValidMaterial("crafter")) {
            if (blockData instanceof Crafter) {
                SubBlockData crafterOrientation = new CrafterOrientationData(blockData);
                blockDataList.add(crafterOrientation);
                SubBlockData crafterCrafting = new CrafterCraftingData(blockData);
                blockDataList.add(crafterCrafting);
                SubBlockData crafterTriggered = new CrafterTriggerData(blockData);
                blockDataList.add(crafterTriggered);
            }
        }

        if (blockData instanceof DaylightDetector) {
            SubBlockData daylightDetectorData = new DaylightDetectorData(blockData);
            blockDataList.add(daylightDetectorData);
        }
        /* NOTE: Not Used
        if (blockData instanceof DecoratedPot) {

        }
        */
        if (blockData instanceof Directional) {
            SubBlockData directionalData = new DirectionalData(blockData);
            blockDataList.add(directionalData);
        }

        if (blockData instanceof Dispenser) {
            SubBlockData dispenserTriggered = new DispenserData(blockData);
            blockDataList.add(dispenserTriggered);
        }

        if (blockData instanceof Door) {
            SubBlockData doorHinge = new DoorData(blockData);
            blockDataList.add(doorHinge);
        }
        /* NOTE: Not Used
        if (blockData instanceof Dripleaf) {

        }
        */
        /* NOTE: Not Used
        if (blockData instanceof EnderChest) {

        }
        */

        if (blockData instanceof EndPortalFrame) {
            SubBlockData endPortalFrameEye = new EndPortalFrameData(blockData);
            blockDataList.add(endPortalFrameEye);
        }

        if (blockData instanceof FaceAttachable) {
            SubBlockData faceAttachable = new FaceAttachableData(blockData);
            blockDataList.add(faceAttachable);
        }

        if (blockData instanceof Farmland) {
            SubBlockData farmlandMoisture = new FarmlandData(blockData);
            blockDataList.add(farmlandMoisture);
        }
        /* NOTE: Not Used
        if (blockData instanceof Fence) {

        }
        */
        /* NOTE: Not Used
        if (blockData instanceof Fire) {

        }
        */
        /* NOTE: Not Used
        if (blockData instanceof Furnace) {

        }
        */
        if (blockData instanceof Gate) {
            SubBlockData gateInWall = new GateData(blockData);
            blockDataList.add(gateInWall);
        }
        /* NOTE: Not Used
        if (blockData instanceof GlassPane) {

        }
        */
        /* NOTE: Not Used
        if (blockData instanceof GlowLichen) {

        }
        */
        /* NOTE: Not Used
        if (blockData instanceof Grindstone) {

        }
        */
        if (blockData instanceof Hangable) {
            SubBlockData hangable = new HangableData(blockData);
            blockDataList.add(hangable);
        }
        /* NOTE: Not Used
        if (blockData instanceof HangingSign) {

        }
        */
        if (isValidMaterial("sniffer_egg")) {
            if (blockData instanceof Hatchable) {
                SubBlockData hatchable = new HatchableData(blockData);
                blockDataList.add(hatchable);
            }
        }
        if (blockData instanceof Hopper) {
            SubBlockData hopperEnabled = new HopperData(blockData);
            blockDataList.add(hopperEnabled);
        }

        if (blockData instanceof Jigsaw) {
            SubBlockData jigsaw = new JigsawData(blockData);
            blockDataList.add(jigsaw);
        }

        if (blockData instanceof Jukebox) {
            SubBlockData jukebox = new JukeboxData(blockData);
            blockDataList.add(jukebox);
        }

        /* NOTE: Not Used
        if (blockData instanceof Ladder) {

        }
        */
        /* NOTE: Not Used
        if (blockData instanceof Lantern) {

        }
        */
        if (blockData instanceof Leaves) {
            SubBlockData leavesDistance = new LeavesDistanceData(blockData);
            blockDataList.add(leavesDistance);
            SubBlockData leavesPersistent = new LeavesPersistentData(blockData);
            blockDataList.add(leavesPersistent);
        }
        if (blockData instanceof Lectern) {
            SubBlockData lecternHasBook = new LecternData(blockData);
            blockDataList.add(lecternHasBook);
        }

        if (blockData instanceof Levelled) {
            SubBlockData levelled = new LevelledData(blockData);
            blockDataList.add(levelled);
        }
        /* NOTE: Not Used
        if (blockData instanceof Light) {

        }
        */
        if (blockData instanceof Lightable) {
            SubBlockData lightable = new LightableData(blockData);
            blockDataList.add(lightable);
        }
        /* NOTE: Not Used
        if (blockData instanceof LightningRod) {

        }
        */
        /* NOTE: Not Used
        if (blockData instanceof MangrovePropagule) {

        }
        */

        if (blockData instanceof MultipleFacing) {
            Set<BlockFace> blockFaces = ((MultipleFacing) blockData).getAllowedFaces();
            if (blockFaces.contains(BlockFace.DOWN)) {
                SubBlockData multipleFacingDown = new MultipleFacingDownData(blockData);
                blockDataList.add(multipleFacingDown);
            }
            if (blockFaces.contains(BlockFace.EAST)) {
                SubBlockData multipleFacingEast = new MultipleFacingEastData(blockData);
                blockDataList.add(multipleFacingEast);
            }
            if (blockFaces.contains(BlockFace.EAST_NORTH_EAST)) {
                SubBlockData multipleFacingEastNorthEast = new MultipleFacingEastNorthEastData(blockData);
                blockDataList.add(multipleFacingEastNorthEast);
            }
            if (blockFaces.contains(BlockFace.EAST_SOUTH_EAST)) {
                SubBlockData multipleFacingEastSouthEast = new MultipleFacingEastSouthEastData(blockData);
                blockDataList.add(multipleFacingEastSouthEast);
            }
            if (blockFaces.contains(BlockFace.NORTH)) {
                SubBlockData multipleFacingNorth = new MultipleFacingNorthData(blockData);
                blockDataList.add(multipleFacingNorth);
            }
            if (blockFaces.contains(BlockFace.NORTH_EAST)) {
                SubBlockData multipleFacingNorthEast = new MultipleFacingNorthEastData(blockData);
                blockDataList.add(multipleFacingNorthEast);
            }
            if (blockFaces.contains(BlockFace.NORTH_NORTH_EAST)) {
                SubBlockData multipleFacingNorthNorthEast = new MultipleFacingNorthNorthEastData(blockData);
                blockDataList.add(multipleFacingNorthNorthEast);
            }
            if (blockFaces.contains(BlockFace.NORTH_NORTH_WEST)) {
                SubBlockData multipleFacingNorthNorthWest = new MultipleFacingNorthNorthWestData(blockData);
                blockDataList.add(multipleFacingNorthNorthWest);
            }
            if (blockFaces.contains(BlockFace.NORTH_WEST)) {
                SubBlockData multipleFacingNorthWest = new MultipleFacingNorthWestData(blockData);
                blockDataList.add(multipleFacingNorthWest);
            }
            if (blockFaces.contains(BlockFace.SELF)) {
                SubBlockData multipleFacingSelf = new MultipleFacingSelfData(blockData);
                blockDataList.add(multipleFacingSelf);
            }
            if (blockFaces.contains(BlockFace.SOUTH)) {
                SubBlockData multipleFacingSouth = new MultipleFacingSouthData(blockData);
                blockDataList.add(multipleFacingSouth);
            }
            if (blockFaces.contains(BlockFace.SOUTH_EAST)) {
                SubBlockData multipleFacingSouthEast = new MultipleFacingSouthEastData(blockData);
                blockDataList.add(multipleFacingSouthEast);
            }
            if (blockFaces.contains(BlockFace.SOUTH_SOUTH_EAST)) {
                SubBlockData multipleFacingSouthSouthEast = new MultipleFacingSouthSouthEastData(blockData);
                blockDataList.add(multipleFacingSouthSouthEast);
            }
            if (blockFaces.contains(BlockFace.SOUTH_SOUTH_WEST)) {
                SubBlockData multipleFacingSouthSouthWest = new MultipleFacingSouthSouthWestData(blockData);
                blockDataList.add(multipleFacingSouthSouthWest);
            }
            if (blockFaces.contains(BlockFace.SOUTH_WEST)) {
                SubBlockData multipleFacingSouthWest = new MultipleFacingSouthWestData(blockData);
                blockDataList.add(multipleFacingSouthWest);
            }
            if (blockFaces.contains(BlockFace.UP)) {
                SubBlockData multipleFacingUp = new MultipleFacingUpData(blockData);
                blockDataList.add(multipleFacingUp);
            }
            if (blockFaces.contains(BlockFace.WEST)) {
                SubBlockData multipleFacingWest = new MultipleFacingWestData(blockData);
                blockDataList.add(multipleFacingWest);
            }
            if (blockFaces.contains(BlockFace.WEST_NORTH_WEST)) {
                SubBlockData multipleFacingWestNorthWest = new MultipleFacingWestNorthWestData(blockData);
                blockDataList.add(multipleFacingWestNorthWest);
            }
            if (blockFaces.contains(BlockFace.WEST_SOUTH_WEST)) {
                SubBlockData multipleFacingWestSouthWest = new MultipleFacingWestSouthWestData(blockData);
                blockDataList.add(multipleFacingWestSouthWest);
            }
        }

        if (blockData instanceof NoteBlock) {
            SubBlockData noteBlockInstrument = new NoteBlockInstrumentData(blockData);
            blockDataList.add(noteBlockInstrument);
            SubBlockData noteBlockNote = new NoteBlockNoteData(blockData);
            blockDataList.add(noteBlockNote);
        }
        /* NOTE: Not Used
        if (blockData instanceof Observer) {

        }
        */
        if (blockData instanceof Orientable) {
            SubBlockData orientable = new OrientableData(blockData);
            blockDataList.add(orientable);
        }

        if (blockData instanceof PinkPetals) {
            SubBlockData pinkPetals = new PinkPetalsData(blockData);
            blockDataList.add(pinkPetals);
        }

        if (blockData instanceof Piston) {
            SubBlockData pistonExtended = new PistonData(blockData);
            blockDataList.add(pistonExtended);
        }

        if (blockData instanceof PistonHead) {
            SubBlockData pistonHeadShort = new PistonHeadData(blockData);
            blockDataList.add(pistonHeadShort);
        }
        /* NOTE: Not Used
        if (blockData instanceof PitcherCrop) {

        }
        */
        if (blockData instanceof PointedDripstone) {
            SubBlockData pointedDripstoneThickness = new PointedDripstoneThicknessData(blockData);
            blockDataList.add(pointedDripstoneThickness);
            SubBlockData pointedDripstoneVerticalDirection = new PointedDripstoneVerticalDirectionData(blockData);
            blockDataList.add(pointedDripstoneVerticalDirection);
        }

        if (blockData instanceof Powerable) {
            SubBlockData powerable = new PowerableData(blockData);
            blockDataList.add(powerable);
        }

        if (blockData instanceof Rail) {
            SubBlockData railShape = new RailData(blockData);
            blockDataList.add(railShape);
        }
        /* NOTE: Not Used
        if (blockData instanceof RedstoneRail) {

        }
        */
        /* NOTE: Not Used
        if (blockData instanceof RedstoneWallTorch) {

        }
        */

        if (blockData instanceof RedstoneWire) {
            SubBlockData redstoneWireNorth = new RedstoneWireNorthData(blockData);
            blockDataList.add(redstoneWireNorth);
            SubBlockData redstoneWireEast = new RedstoneWireEastData(blockData);
            blockDataList.add(redstoneWireEast);
            SubBlockData redstoneWireSouth = new RedstoneWireSouthData(blockData);
            blockDataList.add(redstoneWireSouth);
            SubBlockData redstoneWireWest = new RedstoneWireWestData(blockData);
            blockDataList.add(redstoneWireWest);
        }

        if (blockData instanceof Repeater) {
            SubBlockData repeaterDelayData = new RepeaterDelayData(blockData);
            blockDataList.add(repeaterDelayData);
            SubBlockData repeaterLocked = new RepeaterLockedData(blockData);
            blockDataList.add(repeaterLocked);
        }

        if (blockData instanceof RespawnAnchor) {
            SubBlockData respawnAnchorCharges = new RespawnAnchorData(blockData);
            blockDataList.add(respawnAnchorCharges);
        }

        if (blockData instanceof Rotatable) {
            SubBlockData rotatable = new RotatableData(blockData);
            blockDataList.add(rotatable);
        }

        if (blockData instanceof Sapling) {
            SubBlockData saplingStage = new SaplingData(blockData);
            blockDataList.add(saplingStage);
        }

        if (blockData instanceof Scaffolding) {
            SubBlockData scaffoldingBottom = new ScaffoldingBottomData(blockData);
            blockDataList.add(scaffoldingBottom);
            SubBlockData scaffoldingDistance = new ScaffoldingDistanceData(blockData);
            blockDataList.add(scaffoldingDistance);
        }

        if (blockData instanceof SculkCatalyst) {
            SubBlockData sculkCatalyst = new SculkCatalystData(blockData);
            blockDataList.add(sculkCatalyst);
        }

        if (blockData instanceof SculkSensor) {
            SubBlockData sculkSensor = new SculkSensorData(blockData);
            blockDataList.add(sculkSensor);
        }

        if (blockData instanceof SculkShrieker) {
            SubBlockData shriekerCanSummonData = new SculkShriekerCanSummonData(blockData);
            blockDataList.add(shriekerCanSummonData);
            SubBlockData sculkShriekerShriekingData = new SculkShriekerShriekingData(blockData);
            blockDataList.add(sculkShriekerShriekingData);
        }
        /* NOTE: Not Used
        if (blockData instanceof SculkVein) {

        }
        */
        if (blockData instanceof SeaPickle) {
            SubBlockData seaPickleData = new SeaPickleData(blockData);
            blockDataList.add(seaPickleData);
        }
        /* NOTE: Not Used
        if (blockData instanceof Sign) {

        }
        */
        if (blockData instanceof Slab) {
            SubBlockData slabData = new SlabData(blockData);
            blockDataList.add(slabData);
        }
        /* NOTE: Not Used
        if (blockData instanceof SmallDripleaf) {

        }
        */
        if (blockData instanceof Snow) {
            SubBlockData snowData = new SnowData(blockData);
            blockDataList.add(snowData);
        }

        if (blockData instanceof Snowable) {
            SubBlockData snowableData = new SnowableData(blockData);
            blockDataList.add(snowableData);
        }

        if (blockData instanceof Stairs) {
            SubBlockData stairsData = new StairsData(blockData);
            blockDataList.add(stairsData);
        }

        if (blockData instanceof StructureBlock) {
            SubBlockData structureBlockMode = new StructureBlockData(blockData);
            blockDataList.add(structureBlockMode);
        }

        /* NOTE: Not Used
        if (blockData instanceof Switch) {

        }
        */
        if (blockData instanceof TechnicalPiston) {
            SubBlockData technicalPistonData = new TechnicalPistonData(blockData);
            blockDataList.add(technicalPistonData);
        }

        if (blockData instanceof TNT) {
            SubBlockData tntUnstable = new TNTData(blockData);
            blockDataList.add(tntUnstable);
        }
        /* NOTE: Not Used
        if (blockData instanceof TrapDoor) {

        }
        */
        if (isValidMaterial("trial_spawner")) {
            if (blockData instanceof TrialSpawner) {
                SubBlockData trialSpawnerDelay = new TrialSpawnerData(blockData);
                blockDataList.add(trialSpawnerDelay);
            }
        }
        if (blockData instanceof Tripwire) {
            SubBlockData tripwire = new TripwireData(blockData);
            blockDataList.add(tripwire);
        }
        /* NOTE: Not Used
        if (blockData instanceof TripwireHook) {

        }
        */
        if (blockData instanceof TurtleEgg) {
            SubBlockData turtleEggHatch = new TurtleEggData(blockData);
            blockDataList.add(turtleEggHatch);
        }

        if (blockData instanceof Wall) {
            SubBlockData wallHeightEast = new WallHeightEastData(blockData);
            blockDataList.add(wallHeightEast);
            SubBlockData wallHeightNorth = new WallHeightNorthData(blockData);
            blockDataList.add(wallHeightNorth);
            SubBlockData wallHeightSouth = new WallHeightSouthData(blockData);
            blockDataList.add(wallHeightSouth);
            SubBlockData wallHeightWest = new WallHeightWestData(blockData);
            blockDataList.add(wallHeightWest);
            SubBlockData wallUp = new WallUpData(blockData);
            blockDataList.add(wallUp);
        }

        /* NOTE: Not Used
        if (blockData instanceof WallHangingSign) {

        }

        if (blockData instanceof WallSign) {

        }
        */
        if (blockData instanceof Waterlogged) {
            SubBlockData waterlogged = new WaterloggedData(blockData);
            blockDataList.add(waterlogged);
        }

        blockDataList = filterSubBlockData(blockDataList);
        cache.put(blockData.getMaterial(), blockDataList);

        return blockDataList;
    }
}
