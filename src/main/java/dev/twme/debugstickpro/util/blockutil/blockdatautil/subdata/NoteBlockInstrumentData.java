package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.Instrument;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.NoteBlock;

import java.util.Arrays;
import java.util.List;

public class NoteBlockInstrumentData implements SubBlockData{
    private String NAME = "Note Block";
    private BlockData blockData;
    private Instrument instrument;
    public NoteBlockInstrumentData(BlockData blockData){
        this.blockData = blockData;
        this.instrument = ((NoteBlock) blockData).getInstrument();
    }
    @Override
    public String name() {
        return NAME;
    }

    @Override
    public BlockData getData() {
        return null;
    }

    @Override
    public BlockData getNextData() {
        return null;
    }

    @Override
    public String getAsString() {
        return null;
    }

    @Override
    public String getNextAsString() {
        return null;
    }

    @Override
    public String getDataAsString() {
        return null;
    }

    @Override
    public String getNextDataAsString() {
        return null;
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextInstrument(){
        NoteBlock noteBlock = ((NoteBlock) blockData);
        List<Instrument> instruments = Arrays.stream(Instrument.values()).toList();
        if (instruments.indexOf(instrument) == instruments.size() - 1){
            instrument = instruments.get(0);
        } else {
            instrument = instruments.get(instruments.indexOf(instrument) + 1);
        }
        noteBlock.setInstrument(instrument);
    }
}
