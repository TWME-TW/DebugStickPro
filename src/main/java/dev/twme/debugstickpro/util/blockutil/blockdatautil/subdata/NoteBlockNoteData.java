package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.Note;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.NoteBlock;

public class NoteBlockNoteData implements SubBlockData{
    private String NAME = "Note";
    private BlockData blockData;
    private Note note;
    public NoteBlockNoteData(BlockData blockData){
        this.blockData = blockData;
        this.note = ((NoteBlock) blockData).getNote();
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
        return "Note: " + note;
    }

    @Override
    public String getNextAsString() {
        nextNote();
        return "Note: " + note;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(note);
    }

    @Override
    public String getNextDataAsString() {
        nextNote();
        return String.valueOf(note);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextNote(){
        NoteBlock noteblock = ((NoteBlock) blockData);

    }
}
