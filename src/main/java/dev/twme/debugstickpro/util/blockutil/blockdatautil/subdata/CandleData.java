package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Candle;

public class CandleData implements SubBlockData{
    private BlockData blockData;
    private boolean isUsing = false;
    private int candleCount;
    public CandleData(BlockData blockData){
        this.blockData = blockData;
        this.candleCount = ((Candle)blockData).getCandles();
    }
    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public BlockData getData() {
        return blockData;
    }

    @Override
    public String getAsString() {
        return LangFile.Candle.replace("%data%",getDataAsString());
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(candleCount);
    }


    @Override
    public void setIsUsing(boolean isUsing) {
        this.isUsing = isUsing;
    }

    @Override
    public boolean isUsing() {
        return isUsing;
    }

    @Override
    public SubBlockData nextData(){
        Candle candle = ((Candle) blockData);
        if (candle.getCandles() >= candle.getMaximumCandles()){
            candle.setCandles(1);
        } else {
            candle.setCandles(candle.getCandles() + 1);
        }
        candleCount = candle.getCandles();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Candle)blockData).setCandles(candleCount);
        return blockData;
    }
}
