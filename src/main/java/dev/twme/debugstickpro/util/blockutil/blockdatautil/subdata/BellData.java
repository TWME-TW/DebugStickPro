package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Bell;

public class BellData implements SubBlockData{
    private String NAME = "Bell Attachment";
    private BlockData blockData;
    private Bell.Attachment attachment;
    public BellData(BlockData blockData){
        this.blockData = blockData;
        this.attachment = ((Bell)blockData).getAttachment();
    }
    @Override
    public String NAME() {
        return NAME;
    }

    @Override
    public BlockData getData() {
        return blockData;
    }

    @Override
    public BlockData getNextData() {
        nextAttachment();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Bell Attachment: " + attachment.name();
    }

    @Override
    public String getNextAsString() {
        nextAttachment();
        return "Bell Attachment: " + attachment.name();
    }

    @Override
    public String getDataAsString(BlockData blockData) {
        return attachment.name();
    }

    @Override
    public String getNextDataAsString() {
        nextAttachment();
        return attachment.name();
    }
    private void nextAttachment(){
        Bell bell = (Bell) blockData;
        if (attachment == Bell.Attachment.CEILING){
            bell.setAttachment(Bell.Attachment.DOUBLE_WALL);
        } else if (attachment == Bell.Attachment.DOUBLE_WALL) {
            bell.setAttachment(Bell.Attachment.FLOOR);
        } else if (attachment == Bell.Attachment.FLOOR){
            bell.setAttachment(Bell.Attachment.SINGLE_WALL);
        } else {
            bell.setAttachment(Bell.Attachment.CEILING);
        }
        attachment = bell.getAttachment();
    }
}
