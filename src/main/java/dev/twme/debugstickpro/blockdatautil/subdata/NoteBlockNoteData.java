package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.DebugStickPro;
import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.Note;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.NoteBlock;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoteBlockNoteData implements SubBlockData {
    private BlockData blockData;
    private Note note;
    private boolean isUsing = false;

    public NoteBlockNoteData(BlockData blockData) {
        this.blockData = blockData;
        this.note = ((NoteBlock) blockData).getNote();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.NoteBlockNoteDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
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
    public SubBlockData nextData() {
        String blockNoteData = blockData.getAsString();
        Pattern r = Pattern.compile("([0-9]+)");
        Matcher m = r.matcher(blockNoteData);
        if (blockNoteData.contains("24")) {
            blockNoteData.replace("24", "0");
        } else {

            byte b = Byte.parseByte(m.group(0));
            b++;
            blockNoteData = blockNoteData.replace(m.group(), String.valueOf(b));
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
        blockNoteData = blockNoteData.replace(m.group(0), String.valueOf(m));
        blockData = DebugStickPro.getInstance().getServer().createBlockData(blockNoteData);
        return blockData;
    }
}
