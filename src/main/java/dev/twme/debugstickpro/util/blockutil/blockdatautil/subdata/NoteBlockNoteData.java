package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.DebugStickPro;
import org.bukkit.Note;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.NoteBlock;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoteBlockNoteData implements SubBlockData{
    private String NAME = "Note";
    private BlockData blockData;
    private Note note;
    private boolean isUsing = false;
    public NoteBlockNoteData(BlockData blockData){
        this.blockData = blockData;
        this.note = ((NoteBlock) blockData).getNote();
    }
    @Override
    public String name() {
        return NAME;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }



    @Override
    public String getAsString() {
        return "Note: " + note;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(note);
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
    public SubBlockData nextData(){
        String blockNoteData = blockData.getAsString();
        Pattern r = Pattern.compile("([0-9]+)");
        Matcher m = r.matcher(blockNoteData);
        if (blockNoteData.contains("24")) {
            blockNoteData.replace("24", "0");
        } else {

            byte b = Byte.parseByte(m.group(0));
            b++;
            blockNoteData.replace(m.group(), String.valueOf(b));
        }
        this.blockData = DebugStickPro.getInstance().getServer().createBlockData(blockNoteData);
        this.note = ((NoteBlock) blockData).getNote();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        String blockNoteData = blockData.getAsString();
        Pattern r = Pattern.compile("([0-9]+)");
        Matcher m = r.matcher(blockNoteData);
        blockNoteData.replace(m.group(0), String.valueOf(m));
        blockData = DebugStickPro.getInstance().getServer().createBlockData(blockNoteData);
        return blockData;
    }
}
