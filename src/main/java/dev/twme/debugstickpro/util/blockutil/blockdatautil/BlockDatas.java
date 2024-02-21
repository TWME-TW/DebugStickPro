// package dev.twme.debugstickpro.util.blockutil.blockdatautil;
//
// import dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata.SubBlockData;
// import org.bukkit.block.Block;
// import org.bukkit.block.data.BlockData;
//
// import java.util.ArrayList;
//
// public class BlockDatas {
//     private ArrayList<SubBlockData> blockDatas = new ArrayList<SubBlockData>();
//     private BlockData blockData;
//
//     public BlockDatas(Block block) {
//         this(block.getBlockData());
//     }
//     public BlockDatas(BlockData blockData) {
//         this.blockData = blockData;
//         blockDatas.addAll(BlockDataSeparater.Separate(this.blockData));
//     }
//     public BlockData getBlockData() {
//         return blockData;
//     }
//
//     public BlockDatas getBlockDatas() {
//         return this;
//     }
//
//     public String getAsString() {
//         String str = "";
//         for (SubBlockData subBlockData : this.blockDatas) {
//             str = str + subBlockData.dataName() + ": " + subBlockData.getDataAsString() + " ";
//         }
//         return str;
//     }
// }
//