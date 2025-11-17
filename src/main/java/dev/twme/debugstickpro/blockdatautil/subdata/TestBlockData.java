package dev.twme.debugstickpro.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.TestBlock;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;

public class TestBlockData extends SubBlockData {
    private TestBlock.Mode mode;

    public TestBlockData(BlockData blockData) {
        this.blockData = blockData;
        this.mode = ((TestBlock) blockData).getMode();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.TestBlockDataName;
    }

    @Override
    public String getDataAsString() {
        return mode.name();
    }

    @Override
    public SubBlockData nextData() {
        TestBlock testBlock = ((TestBlock) blockData);
        switch (mode) {
            case START -> mode = TestBlock.Mode.LOG;
            case LOG -> mode = TestBlock.Mode.FAIL;
            case FAIL -> mode = TestBlock.Mode.ACCEPT;
            case ACCEPT -> mode = TestBlock.Mode.START;
        }
        testBlock.setMode(mode);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        TestBlock testBlock = ((TestBlock) blockData);
        switch (mode) {
            case LOG -> mode = TestBlock.Mode.START;
            case FAIL -> mode = TestBlock.Mode.LOG;
            case ACCEPT -> mode = TestBlock.Mode.FAIL;
            case START -> mode = TestBlock.Mode.ACCEPT;
        }
        testBlock.setMode(mode);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((TestBlock) blockData).setMode(mode);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new TestBlockData(blockData);
    }
}
