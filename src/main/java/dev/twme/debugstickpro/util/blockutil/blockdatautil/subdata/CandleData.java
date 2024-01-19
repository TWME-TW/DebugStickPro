package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Candle;

public class CandleData implements SubBlockData{
    private String NAME = "Candle";
    private BlockData blockData;
    private int candleCount;
    public CandleData(BlockData blockData){
        this.blockData = blockData;
        this.candleCount = ((Candle)blockData).getCandles();
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
        nextCandleCount();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Candle Count: " + candleCount;
    }

    @Override
    public String getNextAsString() {
        nextCandleCount();
        return "Candle Count: " + candleCount;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(candleCount);
    }

    @Override
    public String getNextDataAsString() {
        nextCandleCount();
        return String.valueOf(candleCount);
    }

    private void nextCandleCount(){
        Candle candle = ((Candle) blockData);
        if (candle.getCandles() >= candle.getMaximumCandles()){
            candle.setCandles(1);
        } else {
            candle.setCandles(candle.getCandles() + 1);
        }
        candleCount = candle.getCandles();
    }
}
