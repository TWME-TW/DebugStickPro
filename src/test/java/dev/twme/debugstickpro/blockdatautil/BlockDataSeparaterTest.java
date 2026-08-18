package dev.twme.debugstickpro.blockdatautil;

import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Door;
import org.bukkit.block.data.type.PitcherCrop;
import org.bukkit.block.data.type.SmallDripleaf;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.block.data.type.TrapDoor;
import org.junit.Test;

import dev.twme.debugstickpro.config.ConfigFile;

import java.lang.reflect.Proxy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BlockDataSeparaterTest {

    @Test
    public void allowsSingleBlockBisectedProperties() {
        assertTrue(BlockDataSeparater.isBisectedHalfSafeToModify(blockData(Stairs.class)));
        assertTrue(BlockDataSeparater.isBisectedHalfSafeToModify(blockData(TrapDoor.class)));
    }

    @Test
    public void rejectsMultiBlockBisectedProperties() {
        assertFalse(BlockDataSeparater.isBisectedHalfSafeToModify(blockData(Bisected.class)));
        assertFalse(BlockDataSeparater.isBisectedHalfSafeToModify(blockData(Door.class)));
        assertFalse(BlockDataSeparater.isBisectedHalfSafeToModify(blockData(SmallDripleaf.class)));
        assertFalse(BlockDataSeparater.isBisectedHalfSafeToModify(blockData(PitcherCrop.class)));
    }

    @Test
    public void allowsUnsafeBisectedPropertiesWhenConfigured() {
        ConfigFile.BlockDataFilter.AllowUnsafeBisectedData = true;
        try {
            assertTrue(BlockDataSeparater.isBisectedHalfSafeToModify(blockData(Bisected.class)));
            assertTrue(BlockDataSeparater.isBisectedHalfSafeToModify(blockData(Door.class)));
        } finally {
            ConfigFile.BlockDataFilter.AllowUnsafeBisectedData = false;
        }
    }

    @Test
    public void rejectsNonBisectedProperties() {
        assertFalse(BlockDataSeparater.isBisectedHalfSafeToModify(blockData(BlockData.class)));
    }

    private static BlockData blockData(Class<? extends BlockData> type) {
        return (BlockData) Proxy.newProxyInstance(
                type.getClassLoader(),
                new Class<?>[]{type},
                (proxy, method, args) -> {
                    throw new AssertionError("Unexpected BlockData method call: " + method.getName());
                }
        );
    }
}
