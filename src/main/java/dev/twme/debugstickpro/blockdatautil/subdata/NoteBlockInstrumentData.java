package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.Instrument;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.NoteBlock;

import java.util.Arrays;
import java.util.List;

public class NoteBlockInstrumentData implements SubBlockData {
    private final BlockData blockData;
    private Instrument instrument;
    private boolean isUsing = false;

    public NoteBlockInstrumentData(BlockData blockData) {
        this.blockData = blockData;
        this.instrument = ((NoteBlock) blockData).getInstrument();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.NoteBlockInstrumentDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }

    @Override
    public String getDataAsString() {
        return instrument.toString();
    }


    @Override
    public SubBlockData setIsUsing(boolean isUsing) {
        this.isUsing = isUsing;
        return this;
    }

    @Override
    public boolean isUsing() {
        return isUsing;
    }

    @Override
    public SubBlockData nextData() {
        NoteBlock noteBlock = ((NoteBlock) blockData);
        List<Instrument> instruments = Arrays.stream(Instrument.values()).toList();
        if (instruments.indexOf(instrument) == instruments.size() - 1) {
            instrument = instruments.get(0);
        } else {
            instrument = instruments.get(instruments.indexOf(instrument) + 1);
        }
        noteBlock.setInstrument(instrument);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        NoteBlock noteBlock = ((NoteBlock) blockData);
        List<Instrument> instruments = Arrays.stream(Instrument.values()).toList();
        if (instruments.indexOf(instrument) == 0) {
            instrument = instruments.get(instruments.size() - 1);
        } else {
            instrument = instruments.get(instruments.indexOf(instrument) - 1);
        }
        noteBlock.setInstrument(instrument);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((NoteBlock) blockData).setInstrument(instrument);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new NoteBlockInstrumentData(blockData);
    }
}
