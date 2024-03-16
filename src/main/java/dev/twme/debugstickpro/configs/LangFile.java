package dev.twme.debugstickpro.configs;

public class LangFile {

    public static int LangFileVersion;

    public static class CommandsMessages {
        public static class Help {
            public static String Title;
            public static String Description;
            public static String Usage;
        }

        public static class Reload {
            public static String Success;
        }

        public static class Give {
            public static String NoPlayer;
            public static String Success;
        }
        public static String NoPermission;
        public static String YouAreNotPlayer;
    }

    public static class ActionBar {
        public static String SelectedDataFormat;
        public static String formatSelectedData(String key, String value){
            return SelectedDataFormat.replace("%key%", key).replace("%value%", value);
        }

        public static String NotSelectedDataFormat;
        public static String formatNotSelectedData(String key, String value){
            return NotSelectedDataFormat.replace("%key%", key).replace("%value%", value);
        }

        public static String CopiedBlockDataFormat;
        public static String formatCopiedBlockData(String key, String value){
            return CopiedBlockDataFormat.replace("%key%", key).replace("%value%", value);
        }
    }

    public static class Tips {
        public static String copyModeIntroduction;
        public static String freezeModeIntroduction;
    }

    public static class DataKeyName {

        public static String AgeableDataName;
        public static String AnaloguePowerableDataName;
        public static String AttachableDataName;
        public static String BambooDataName;
        public static String BedDataName;
        public static String BeehiveDataName;
        public static String BellDataName;
        public static String BigDripleafDataName;
        public static String BisectedDataName;
        public static String BrewingStandBottle_0DataName;
        public static String BrewingStandBottle_1DataName;
        public static String BrewingStandBottle_2DataName;
        public static String BrushableDataName;
        public static String BubbleColumnDataName;
        public static String CakeDataName;
        public static String CampfireDataName;
        public static String CandleDataName;
        public static String CaveVinesPlantDataName;
        public static String ChestDataName;
        public static String ChiseledBookshelfSlot_0DataName;
        public static String ChiseledBookshelfSlot_1DataName;
        public static String ChiseledBookshelfSlot_2DataName;
        public static String ChiseledBookshelfSlot_3DataName;
        public static String ChiseledBookshelfSlot_4DataName;
        public static String ChiseledBookshelfSlot_5DataName;
        public static String CommandBlockDataName;
        public static String ComparatorDataName;
        public static String CrafterCraftingDataName;
        public static String CrafterOrientationDataName;
        public static String CrafterTriggerDataName;
        public static String DaylightDetectorDataName;
        public static String DirectionalDataName;
        public static String DispenserDataName;
        public static String DoorDataName;
        public static String EndPortalFrameDataName;
        public static String FaceAttachableDataName;
        public static String FarmlandDataName;
        public static String GateDataName;
        public static String HangableDataName;
        public static String HatchableDataName;
        public static String HopperDataName;
        public static String JigsawDataName;
        public static String JukeboxDataName;
        public static String LeavesDistanceDataName;
        public static String LeavesPersistentDataName;
        public static String LectronDataName;
        public static String LevelledDataName;
        public static String LightableDataName;
        public static String MultipleFacingDownDataName;
        public static String MultipleFacingEastDataName;
        public static String MultipleFacingEastNorthEastDataName;
        public static String MultipleFacingEastSouthEastDataName;
        public static String MultipleFacingNorthDataName;
        public static String MultipleFacingNorthEastDataName;
        public static String MultipleFacingNorthNorthEastDataName;
        public static String MultipleFacingNorthNorthWestDataName;
        public static String MultipleFacingNorthWestDataName;
        public static String MultipleFacingSelfDataName;
        public static String MultipleFacingSouthDataName;
        public static String MultipleFacingSouthEastDataName;
        public static String MultipleFacingSouthSouthEastDataName;
        public static String MultipleFacingSouthSouthWestDataName;
        public static String MultipleFacingSouthWestDataName;
        public static String MultipleFacingUpDataName;
        public static String MultipleFacingWestDataName;
        public static String MultipleFacingWestNorthWestDataName;
        public static String MultipleFacingWestSouthWestDataName;
        public static String NoteBlockInstrumentDataName;
        public static String NoteBlockNoteDataName;
        public static String OrientableDataName;
        public static String PinkPetalsDataname;
        public static String PistonDataName;
        public static String PistonHeadDataName;
        public static String PointedDripstoneThicknessDataName;
        public static String PointedDripstoneVerticalDirectionDataName;
        public static String PowerableDataName;
        public static String RailDataName;
        public static String RedstoneWireEastDataName;
        public static String RedstoneWireNorthDataName;
        public static String RedstoneWireSouthDataName;
        public static String RedstoneWireWestDataName;

        public static String RepeaterDelayDataName;
        public static String RepeaterLockedDataName;
        public static String RespawnAnchorDataName;
        public static String RotatableDataName;
        public static String SaplingDataName;
        public static String ScaffoldingBottomDataName;
        public static String ScaffoldingDistanceDataName;
        public static String SculkCatalystDataName;
        public static String SculkSensorDataName;
        public static String SculkShriekerCanSummonDataName;
        public static String SculkShriekerShriekingDataName;
        public static String SeaPickleDataName;
        public static String SlabDataName;
        public static String SnowableDataName;
        public static String SnowDataName;
        public static String StairsDataName;
        public static String StructureBlockDataName;
        public static String TechnicalPistonDataName;
        public static String TNTDataName;
        public static String TrialSpawnerDataName;
        public static String TripwireDataName;
        public static String TurtleEggDataName;
        public static String WallHeightEastDataName;
        public static String WallHeightNorthDataName;
        public static String WallHeightSouthDataName;
        public static String WallHeightWestDataName;
        public static String WallUpDataName;
        public static String WaterloggedDataName;

    }
}
