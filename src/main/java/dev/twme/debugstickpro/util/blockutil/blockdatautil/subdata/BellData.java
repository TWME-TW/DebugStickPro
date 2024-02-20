package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Bell;

public class BellData implements SubBlockData {
    private BlockData blockData;
    private Bell.Attachment attachment;
    private boolean isUsing = false;

    public BellData(BlockData blockData) {
        this.blockData = blockData;
        this.attachment = ((Bell) blockData).getAttachment();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.BellDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return attachment.name();
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
        Bell bell = (Bell) blockData;
        if (attachment == Bell.Attachment.CEILING) {
            bell.setAttachment(Bell.Attachment.DOUBLE_WALL);
        } else if (attachment == Bell.Attachment.DOUBLE_WALL) {
            bell.setAttachment(Bell.Attachment.FLOOR);
        } else if (attachment == Bell.Attachment.FLOOR) {
            bell.setAttachment(Bell.Attachment.SINGLE_WALL);
        } else {
            bell.setAttachment(Bell.Attachment.CEILING);
        }
        attachment = bell.getAttachment();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Bell) blockData).setAttachment(attachment);
        return blockData;
    }
}
