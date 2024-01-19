package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.MultipleFacing;

import java.util.List;

public class MultipleFacingData implements SubBlockData{
    private String NAME = "Multiple Facing";
    private BlockData blockData;
    private BlockFace face;
    public MultipleFacingData(BlockData blockData){
        this.blockData = blockData;
        this.face = ((MultipleFacing) blockData).getFaces().stream().toList().get(0);
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
        nextFace();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Face: " + face;
    }

    @Override
    public String getNextAsString() {
        nextFace();
        return "Face: " + face;
    }

    @Override
    public String getDataAsString() {
        return face.toString();
    }

    @Override
    public String getNextDataAsString() {
        return face.toString();
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextFace(){
        List<BlockFace> bf = ((MultipleFacing) blockData).getAllowedFaces().stream().toList();
        if (bf.indexOf(face) == bf.size() - 1){
            face = bf.get(0);
        } else {
            face = bf.get(bf.indexOf(face) + 1);
        }
        ((MultipleFacing) blockData).setFace(face, true);
    }
}
