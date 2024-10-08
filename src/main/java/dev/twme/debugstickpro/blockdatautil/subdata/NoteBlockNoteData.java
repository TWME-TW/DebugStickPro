package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.DebugStickPro;
import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.Note;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.NoteBlock;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoteBlockNoteData extends SubBlockData {
    private Note note;

    public NoteBlockNoteData(BlockData blockData) {
        this.blockData = blockData;
        this.note = ((NoteBlock) blockData).getNote();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.NoteBlockNoteDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(note);
    }

    @Override
    public SubBlockData nextData() {
        String blockNoteData = blockData.getAsString();

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(blockNoteData);
        matcher.find();
        String number = matcher.group(0);
        int num = Integer.parseInt(number);
        if (num == 24) {
            num = 0;
        } else {
            num++;
        }
        blockNoteData = blockNoteData.replace(number, String.valueOf(num));
        this.blockData = DebugStickPro.getInstance().getServer().createBlockData(blockNoteData);
        this.note = ((NoteBlock) blockData).getNote();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        String blockNoteData = blockData.getAsString();

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(blockNoteData);
        matcher.find();
        String number = matcher.group(0);
        int num = Integer.parseInt(number);
        if (num == 0) {
            num = 24;
        } else {
            num--;
        }
        blockNoteData = blockNoteData.replace(number, String.valueOf(num));
        this.blockData = DebugStickPro.getInstance().getServer().createBlockData(blockNoteData);
        this.note = ((NoteBlock) blockData).getNote();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        // 將該 BlockData 文字化
        String blockNoteData = blockData.getAsString();

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(blockNoteData);
        matcher.find();
        String number = matcher.group(0);

        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(this.blockData.getAsString());
        m.find();
        String num = m.group(0);

        // 將該 BlockData 的音符數值替換成目前的音符數值
        blockNoteData = blockNoteData.replace(number, num);

        // 將文字化的 BlockData 轉換成 BlockData 物件
        blockData = DebugStickPro.getInstance().getServer().createBlockData(blockNoteData);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new NoteBlockNoteData(blockData);
    }
}
