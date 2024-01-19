package dev.twme.debugstickpro.util.blockutil.blockdatautil;

import dev.twme.debugstickpro.util.Log;
import dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata.*;
import org.bukkit.block.data.*;
import org.bukkit.block.data.type.*;

import java.util.ArrayList;


public class BlockDataSeparater {
    public static ArrayList<SubBlockData> Separate(BlockData blockData) {


        ArrayList<SubBlockData> blockDataList = new ArrayList<SubBlockData>();

        if (blockData instanceof Ageable) {
            SubBlockData ageableUtil = new AgeableData(blockData);
            blockDataList.add(ageableUtil);
        }

        /* NOTE: Not Used
        if (blockData instanceof AmethystCluster) {
            Log.info("AmethystCluster");
        }
        */

        if (blockData instanceof AnaloguePowerable) {
            SubBlockData analoguePowerableData = new AnaloguePowerableData(blockData);
            blockDataList.add(analoguePowerableData);
        }

        if (blockData instanceof Attachable) {
            SubBlockData attachableData = new AttachableData(blockData);
            blockDataList.add(attachableData);
        }

        if (blockData instanceof Bamboo) {
            SubBlockData bambooData = new BambooData(blockData);
            blockDataList.add(bambooData);
        }

        /* NOTE: Not Used
        if (blockData instanceof Barrel) {
            Log.info("Barrel");
        }
        */

        if (blockData instanceof Bed) {
            SubBlockData bedData = new BedData(blockData);
            blockDataList.add(bedData);
        }

        if (blockData instanceof Beehive) {
            SubBlockData beehiveData = new BeehiveData(blockData);
            blockDataList.add(beehiveData);
        }
        if (blockData instanceof Bell) {
            SubBlockData bellData = new BellData(blockData);
            blockDataList.add(bellData);
        }
        if (blockData instanceof BigDripleaf) {
            SubBlockData bigDripleafData = new BigDripleafData(blockData);
            blockDataList.add(bigDripleafData);
        }

        if (blockData instanceof Bisected) {
            SubBlockData bisectedData = new BisectedData(blockData);
            blockDataList.add(bisectedData);
        }

        if (blockData instanceof BrewingStand) {
            SubBlockData brewingStandBottle_0 = new BrewingStandBottle_0(blockData);
            SubBlockData brewingStandBottle_1 = new BrewingStandBottle_1(blockData);
            SubBlockData brewingStandBottle_2 = new BrewingStandBottle_2(blockData);
            blockDataList.add(brewingStandBottle_0);
            blockDataList.add(brewingStandBottle_1);
            blockDataList.add(brewingStandBottle_2);
        }

        if (blockData instanceof Brushable) {
            SubBlockData brushable = new BrushableData(blockData);
            blockDataList.add(brushable);
        }

        if (blockData instanceof BubbleColumn) {
            SubBlockData bubbleColumn = new BrushableData(blockData);
            blockDataList.add(bubbleColumn);
        }

        if (blockData instanceof Cake) {
            SubBlockData cake = new CakeData(blockData);
            blockDataList.add(cake);
        }

        /* NOTE: Not Used
        if (blockData instanceof CalibratedSculkSensor) {
            Log.info("CalibratedSculkSensor");
        }
        */

        if (blockData instanceof Campfire) {
            SubBlockData campfire = new CampfireData(blockData);
            blockDataList.add(campfire);
        }

        if (blockData instanceof Candle) {
            SubBlockData candle = new CandleData(blockData);
            blockDataList.add(candle);
        }
        /* NOTE: Not Used
        if (blockData instanceof CaveVines) {
            Log.info("CaveVines");
        }
        */
        if (blockData instanceof CaveVinesPlant) {
            SubBlockData caveVinesPlant = new CaveVinesPlantData(blockData);
            blockDataList.add(caveVinesPlant);
        }
        /* NOTE: Not Used
        if (blockData instanceof Chain) {

        }
        */
        if (blockData instanceof Chest) {
            SubBlockData chest = new ChestData(blockData);
            blockDataList.add(chest);
        }

        if (blockData instanceof ChiseledBookshelf) {

        }

        if (blockData instanceof Cocoa) {

        }

        if (blockData instanceof CommandBlock) {

        }

        if (blockData instanceof Comparator) {

        }

        if (blockData instanceof CopperBulb) {

        }

        if (blockData instanceof CoralWallFan) {

        }

        if (blockData instanceof Crafter) {

        }

        if (blockData instanceof DaylightDetector) {

        }

        if (blockData instanceof DecoratedPot) {

        }

        if (blockData instanceof Directional) {

        }

        if (blockData instanceof Dispenser) {

        }

        if (blockData instanceof Door) {

        }

        if (blockData instanceof Dripleaf) {

        }

        if (blockData instanceof EnderChest) {

        }

        if (blockData instanceof EndPortalFrame) {

        }

        if (blockData instanceof FaceAttachable) {

        }

        if (blockData instanceof Farmland) {

        }

        if (blockData instanceof Fence) {

        }

        if (blockData instanceof Fire) {

        }

        if (blockData instanceof Furnace) {

        }

        if (blockData instanceof Gate) {

        }

        if (blockData instanceof GlassPane) {

        }

        if (blockData instanceof GlowLichen) {

        }

        if (blockData instanceof Grindstone) {

        }

        if (blockData instanceof Hangable) {

        }

        if (blockData instanceof HangingSign) {

        }

        if (blockData instanceof Hatchable) {

        }

        if (blockData instanceof Hopper) {

        }

        if (blockData instanceof Jigsaw) {

        }

        if (blockData instanceof Jukebox) {

        }

        if (blockData instanceof Ladder) {

        }

        if (blockData instanceof Lantern) {

        }

        if (blockData instanceof Leaves) {

        }

        if (blockData instanceof Lectern) {

        }

        if (blockData instanceof Levelled) {

        }

        if (blockData instanceof Light) {

        }

        if (blockData instanceof Lightable) {

        }

        if (blockData instanceof LightningRod) {

        }

        if (blockData instanceof MangrovePropagule) {

        }

        if (blockData instanceof MultipleFacing) {

        }

        if (blockData instanceof NoteBlock) {

        }

        if (blockData instanceof Observer) {

        }

        if (blockData instanceof Orientable) {

        }

        if (blockData instanceof PinkPetals) {

        }

        if (blockData instanceof Piston) {

        }

        if (blockData instanceof Piston) {

        }

        if (blockData instanceof PistonHead) {

        }

        if (blockData instanceof PitcherCrop) {

        }

        if (blockData instanceof PointedDripstone) {

        }

        if (blockData instanceof Powerable) {

        }

        if (blockData instanceof Rail) {

        }

        if (blockData instanceof RedstoneRail) {

        }

        if (blockData instanceof RedstoneWallTorch) {

        }

        if (blockData instanceof RedstoneWire) {

        }

        if (blockData instanceof Repeater) {

        }

        if (blockData instanceof RespawnAnchor) {

        }

        if (blockData instanceof Rotatable) {

        }

        if (blockData instanceof Sapling) {

        }

        if (blockData instanceof Scaffolding) {

        }

        if (blockData instanceof SculkCatalyst) {

        }

        if (blockData instanceof SculkSensor) {

        }

        if (blockData instanceof SculkShrieker) {

        }

        if (blockData instanceof SculkVein) {

        }

        if (blockData instanceof SeaPickle) {

        }

        if (blockData instanceof Sign) {

        }

        if (blockData instanceof Slab) {

        }

        if (blockData instanceof SmallDripleaf) {

        }

        if (blockData instanceof Snow) {

        }

        if (blockData instanceof Snowable) {

        }

        if (blockData instanceof Stairs) {

        }

        if (blockData instanceof StructureBlock) {

        }

        if (blockData instanceof Switch) {

        }

        if (blockData instanceof TechnicalPiston) {

        }

        if (blockData instanceof TNT) {

        }

        if (blockData instanceof TrapDoor) {

        }

        if (blockData instanceof TrialSpawner) {

        }

        if (blockData instanceof Tripwire) {

        }

        if (blockData instanceof TripwireHook) {

        }

        if (blockData instanceof TurtleEgg) {

        }

        if (blockData instanceof Wall) {

        }

        if (blockData instanceof WallHangingSign) {

        }

        if (blockData instanceof WallSign) {

        }

        if (blockData instanceof Waterlogged) {
            Log.info("Waterlogged");
        }


        return blockDataList;
    }
}
