package dev.twme.debugstickpro.util.blockutil.blockdatautil;

import dev.twme.debugstickpro.util.Log;
import dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.*;
import org.bukkit.block.data.type.*;

import java.util.ArrayList;


public class BlockDataSeparater {
    public static ArrayList<SubBlockData> Separate(Block block) {
        BlockData blockData = block.getBlockData();

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
        //TODO: ReDesign ChiseledBookshelf
        if (blockData instanceof ChiseledBookshelf) {
            SubBlockData chiseledBookshelfSlot_0 = new ChiseledBookshelfSlot_0(blockData);
            SubBlockData chiseledBookshelfSlot_1 = new ChiseledBookshelfSlot_1(blockData);
            SubBlockData chiseledBookshelfSlot_2 = new ChiseledBookshelfSlot_2(blockData);
            SubBlockData chiseledBookshelfSlot_3 = new ChiseledBookshelfSlot_3(blockData);
            SubBlockData chiseledBookshelfSlot_4 = new ChiseledBookshelfSlot_4(blockData);
            SubBlockData chiseledBookshelfSlot_5 = new ChiseledBookshelfSlot_5(blockData);
            blockDataList.add(chiseledBookshelfSlot_0);
            blockDataList.add(chiseledBookshelfSlot_1);
            blockDataList.add(chiseledBookshelfSlot_2);
            blockDataList.add(chiseledBookshelfSlot_3);
            blockDataList.add(chiseledBookshelfSlot_4);
            blockDataList.add(chiseledBookshelfSlot_5);
        }
        /* NOTE: Not Used
        if (blockData instanceof Cocoa) {

        }
        */
        if (blockData instanceof CommandBlock) {
            SubBlockData commandBlockConditional = new CommandBlockData(blockData);
            blockDataList.add(commandBlockConditional);
        }

        if (blockData instanceof Comparator) {
            SubBlockData comparatorMode = new ComparatorData(blockData);
            blockDataList.add(comparatorMode);
        }
        /* NOTE: Not Used
        if (blockData instanceof CopperBulb) {

        }
        */
        /* NOTE: Not Used
        if (blockData instanceof CoralWallFan) {

        }
        */
        if (blockData instanceof Crafter) {
            SubBlockData crafterOrientation = new CrafterOrientationData(blockData);
            blockDataList.add(crafterOrientation);
            SubBlockData crafterCrafting = new CrafterCrafting(blockData);
            blockDataList.add(crafterCrafting);
            SubBlockData crafterTriggered = new CrafterTriggerData(blockData);
            blockDataList.add(crafterTriggered);
        }

        if (blockData instanceof DaylightDetector) {
            SubBlockData daylightDetectorData = new DaylightDetectorData(blockData);
            blockDataList.add(daylightDetectorData);
        }
        /* NOTE: Not Used
        if (blockData instanceof DecoratedPot) {

        }
        */
        if (blockData instanceof Directional) {
            SubBlockData directionalData = new DirectionalData(blockData);
            blockDataList.add(directionalData);
        }

        if (blockData instanceof Dispenser) {
            SubBlockData dispenserTriggered = new DispenserData(blockData);
            blockDataList.add(dispenserTriggered);
        }

        if (blockData instanceof Door) {
            SubBlockData doorHinge = new DoorData(blockData);
            blockDataList.add(doorHinge);
        }
        /* NOTE: Not Used
        if (blockData instanceof Dripleaf) {

        }
        */
        /* NOTE: Not Used
        if (blockData instanceof EnderChest) {

        }
        */

        if (blockData instanceof EndPortalFrame) {
            SubBlockData endPortalFrameEye = new EndPortalFrameData(blockData);
            blockDataList.add(endPortalFrameEye);
        }

        if (blockData instanceof FaceAttachable) {
            SubBlockData faceAttachable = new FaceAttachableData(blockData);
            blockDataList.add(faceAttachable);
        }

        if (blockData instanceof Farmland) {
            SubBlockData farmlandMoisture = new FarmlandData(blockData);
            blockDataList.add(farmlandMoisture);
        }
        /* NOTE: Not Used
        if (blockData instanceof Fence) {

        }
        */
        /* NOTE: Not Used
        if (blockData instanceof Fire) {

        }
        */
        /* NOTE: Not Used
        if (blockData instanceof Furnace) {

        }
        */
        if (blockData instanceof Gate) {
            SubBlockData gateInWall = new GateData(blockData);
            blockDataList.add(gateInWall);
        }
        /* NOTE: Not Used
        if (blockData instanceof GlassPane) {

        }
        */
        /* NOTE: Not Used
        if (blockData instanceof GlowLichen) {

        }
        */
        /* NOTE: Not Used
        if (blockData instanceof Grindstone) {

        }
        */
        if (blockData instanceof Hangable) {
            SubBlockData hangable = new HangableData(blockData);
            blockDataList.add(hangable);
        }
        /* NOTE: Not Used
        if (blockData instanceof HangingSign) {

        }
        */
        if (blockData instanceof Hatchable) {
            SubBlockData hatchable = new HatchableData(blockData);
            blockDataList.add(hatchable);
        }

        if (blockData instanceof Hopper) {
            SubBlockData hopperEnabled = new HopperData(blockData);
            blockDataList.add(hopperEnabled);
        }

        if (blockData instanceof Jigsaw) {
            SubBlockData jigsaw = new JigsawData(blockData);
            blockDataList.add(jigsaw);
        }
        /*TODO: ReDesign JigsawOrientation
        if (blockData instanceof Jukebox) {

        }
        */
        /* NOTE: Not Used
        if (blockData instanceof Ladder) {

        }
        */
        /* NOTE: Not Used
        if (blockData instanceof Lantern) {

        }
        */
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
