package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Candle;

public class CandleData implements SubBlockData {
    private BlockData blockData;
    private boolean isUsing = false;
    private int candleCount;

    public CandleData(BlockData blockData) {
        this.blockData = blockData;
        this.candleCount = ((Candle) blockData).getCandles();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.CandleDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(candleCount);
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
        Candle candle = ((Candle) blockData);
        if (candle.getCandles() >= candle.getMaximumCandles()) {
            candle.setCandles(1);
        } else {
            candle.setCandles(candle.getCandles() + 1);
        }
        candleCount = candle.getCandles();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        Candle candle = ((Candle) blockData);
        if (candle.getCandles() <= 1) {
            candle.setCandles(candle.getMaximumCandles());
        } else {
            candle.setCandles(candle.getCandles() - 1);
        }
        candleCount = candle.getCandles();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Candle) blockData).setCandles(candleCount);
        return blockData;
    }

    @Override
    public SubBlockData getDataFac(BlockData blockData) {
        return new CandleData(blockData);
    }
}
