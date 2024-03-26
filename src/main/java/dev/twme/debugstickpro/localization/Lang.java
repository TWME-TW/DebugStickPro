package dev.twme.debugstickpro.localization;

public class Lang {
    public final static String LangFileVersion = "LangFileVersion";

    public final static class CommandsMessages {
        public final static class Help {
            public final static String HelpMessage = "CommandsMessages.Help.HelpMessage";
        }

        public final static class Reload {
            public final static String Success = "CommandsMessages.Reload.Success";
        }

        public final static class Give {
            public final static String NoPlayer = "CommandsMessages.Give.NoPlayer";
            public final static String Success = "CommandsMessages.Give.Success";
        }

        public final static class Mode {
            public final static String Usage = "CommandsMessages.Mode.Usage";
            public final static String SuccessSetToClassic = "CommandsMessages.Mode.SuccessSetToClassic";
            public final static String SuccessSetToCopy = "CommandsMessages.Mode.SuccessSetToCopy";
            public final static String SuccessSetToFreeze = "CommandsMessages.Mode.SuccessSetToFreeze";
        }

        public final static String NoPermission = "CommandsMessages.NoPermission";
        public final static String YouAreNotPlayer = "CommandsMessages.YouAreNotPlayer";
    }

    public final static class ActionBar {
        public final static String SelectedDataFormat = "ActionBar.SelectedDataFormat";

        public static String formatSelectedData(String actionBar, String key, String value) {
            return actionBar.replace("%key%", key).replace("%value%", value);
        }

        public final static String NotSelectedDataFormat = "ActionBar.NotSelectedDataFormat";

        public static String formatNotSelectedData(String actionBar, String key, String value) {
            return actionBar.replace("%key%", key).replace("%value%", value);
        }

        public final static String CopiedBlockDataFormat = "ActionBar.CopiedBlockDataFormat";

        public static String formatCopiedBlockData(String actionBar, String key, String value) {
            return actionBar.replace("%key%", key).replace("%value%", value);
        }

        public final static String FreezeBlockCount = "ActionBar.FreezeBlockCount";

        public static String formatFreezeBlockCount(String actionBar, int count) {
            return actionBar.replace("%count%", String.valueOf(count));
        }
    }

    public final static class Tips {
        public final static String classicModeIntroduction = "Tips.ClassicModeIntroduction";
        public final static String copyModeIntroduction = "Tips.CopyModeIntroduction";
        public final static String freezeModeIntroduction = "Tips.FreezeModeIntroduction";
    }

    public final static class DataKeyName {

        public final static String AgeableDataName = "DataKeyName.AgeableDataName";
        public final static String AnaloguePowerableDataName = "DataKeyName.AnaloguePowerableDataName";
        public final static String AttachableDataName = "DataKeyName.AttachableDataName";
        public final static String BambooDataName = "DataKeyName.BambooDataName";
        public final static String BedDataName = "DataKeyName.BedDataName";
        public final static String BeehiveDataName = "DataKeyName.BeehiveDataName";
        public final static String BellDataName = "DataKeyName.BellDataName";
        public final static String BigDripleafDataName = "DataKeyName.BigDripleafDataName";
        public final static String BisectedDataName = "DataKeyName.BisectedDataName";
        public final static String BrewingStandBottle_0DataName = "DataKeyName.BrewingStandBottle_0DataName";
        public final static String BrewingStandBottle_1DataName = "DataKeyName.BrewingStandBottle_1DataName";
        public final static String BrewingStandBottle_2DataName = "DataKeyName.BrewingStandBottle_2DataName";
        public final static String BrushableDataName = "DataKeyName.BrushableDataName";
        public final static String BubbleColumnDataName = "DataKeyName.BubbleColumnDataName";
        public final static String CakeDataName = "DataKeyName.CakeDataName";
        public final static String CampfireDataName = "DataKeyName.CampfireDataName";
        public final static String CandleDataName = "DataKeyName.CandleDataName";
        public final static String CaveVinesPlantDataName = "DataKeyName.CaveVinesPlantDataName";
        public final static String ChestDataName = "DataKeyName.ChestDataName";
        public final static String ChiseledBookshelfSlot_0DataName = "DataKeyName.ChiseledBookshelfSlot_0DataName";
        public final static String ChiseledBookshelfSlot_1DataName = "DataKeyName.ChiseledBookshelfSlot_1DataName";
        public final static String ChiseledBookshelfSlot_2DataName = "DataKeyName.ChiseledBookshelfSlot_2DataName";
        public final static String ChiseledBookshelfSlot_3DataName = "DataKeyName.ChiseledBookshelfSlot_3DataName";
        public final static String ChiseledBookshelfSlot_4DataName = "DataKeyName.ChiseledBookshelfSlot_4DataName";
        public final static String ChiseledBookshelfSlot_5DataName = "DataKeyName.ChiseledBookshelfSlot_5DataName";
        public final static String CommandBlockDataName = "DataKeyName.CommandBlockDataName";
        public final static String ComparatorDataName = "DataKeyName.ComparatorDataName";
        public final static String CrafterCraftingDataName = "DataKeyName.CrafterCraftingDataName";
        public final static String CrafterOrientationDataName = "DataKeyName.CrafterOrientationDataName";
        public final static String CrafterTriggerDataName = "DataKeyName.CrafterTriggerDataName";
        public final static String DaylightDetectorDataName = "DataKeyName.DaylightDetectorDataName";
        public final static String DirectionalDataName = "DataKeyName.DirectionalDataName";
        public final static String DispenserDataName = "DataKeyName.DispenserDataName";
        public final static String DoorDataName = "DataKeyName.DoorDataName";
        public final static String EndPortalFrameDataName = "DataKeyName.EndPortalFrameDataName";
        public final static String FaceAttachableDataName = "DataKeyName.FaceAttachableDataName";
        public final static String FarmlandDataName = "DataKeyName.FarmlandDataName";
        public final static String GateDataName = "DataKeyName.GateDataName";
        public final static String HangableDataName = "DataKeyName.HangableDataNam";
        public final static String HatchableDataName = "DataKeyName.HatchableDataName";
        public final static String HopperDataName = "DataKeyName.HopperDataName";
        public final static String JigsawDataName = "DataKeyName.JigsawDataName";
        public final static String JukeboxDataName = "DataKeyName.JukeboxDataName";
        public final static String LeavesDistanceDataName = "DataKeyName.LeavesDistanceDataName";
        public final static String LeavesPersistentDataName = "DataKeyName.LeavesPersistentDataName";
        public final static String LectronDataName = "DataKeyName.LectronDataName";
        public final static String LevelledDataName = "DataKeyName.LevelledDataName";
        public final static String LightableDataName = "DataKeyName.LightableDataName";
        public final static String MultipleFacingDownDataName = "DataKeyName.MultipleFacingDownDataName";
        public final static String MultipleFacingEastDataName = "DataKeyName.MultipleFacingEastDataName";
        public final static String MultipleFacingEastNorthEastDataName = "DataKeyName.MultipleFacingEastNorthEastDataName";
        public final static String MultipleFacingEastSouthEastDataName = "DataKeyName.MultipleFacingEastSouthEastDataName";
        public final static String MultipleFacingNorthDataName = "DataKeyName.MultipleFacingNorthDataName";
        public final static String MultipleFacingNorthEastDataName = "DataKeyName.MultipleFacingNorthEastDataName";
        public final static String MultipleFacingNorthNorthEastDataName = "DataKeyName.MultipleFacingNorthNorthEastDataName";
        public final static String MultipleFacingNorthNorthWestDataName = "DataKeyName.MultipleFacingNorthNorthWestDataName";
        public final static String MultipleFacingNorthWestDataName = "DataKeyName.MultipleFacingNorthWestDataName";
        public final static String MultipleFacingSelfDataName = "DataKeyName.MultipleFacingSelfDataName";
        public final static String MultipleFacingSouthDataName = "DataKeyName.MultipleFacingSouthDataName";
        public final static String MultipleFacingSouthEastDataName = "DataKeyName.MultipleFacingSouthEastDataName";
        public final static String MultipleFacingSouthSouthEastDataName = "DataKeyName.MultipleFacingSouthSouthEastDataName";
        public final static String MultipleFacingSouthSouthWestDataName = "DataKeyName.MultipleFacingSouthSouthWestDataName";
        public final static String MultipleFacingSouthWestDataName = "DataKeyName.MultipleFacingSouthWestDataName";
        public final static String MultipleFacingUpDataName = "DataKeyName.MultipleFacingUpDataName";
        public final static String MultipleFacingWestDataName = "DataKeyName.MultipleFacingWestDataName";
        public final static String MultipleFacingWestNorthWestDataName = "DataKeyName.MultipleFacingWestNorthWestDataName";
        public final static String MultipleFacingWestSouthWestDataName = "DataKeyName.MultipleFacingWestSouthWestDataName";
        public final static String NoteBlockInstrumentDataName = "DataKeyName.NoteBlockInstrumentDataName";
        public final static String NoteBlockNoteDataName = "DataKeyName.NoteBlockNoteDataName";
        public final static String OrientableDataName = "DataKeyName.OrientableDataName";
        public final static String PinkPetalsDataname = "DataKeyName.PinkPetalsDataname";
        public final static String PistonDataName = "DataKeyName.PistonDataName";
        public final static String PistonHeadDataName = "DataKeyName.PistonHeadDataName";
        public final static String PointedDripstoneThicknessDataName = "DataKeyName.PointedDripstoneThicknessDataName";
        public final static String PointedDripstoneVerticalDirectionDataName = "DataKeyName.PointedDripstoneVerticalDirectionDataName";
        public final static String PowerableDataName = "DataKeyName.PowerableDataName";
        public final static String RailDataName = "DataKeyName.RailDataName";
        public final static String RedstoneWireEastDataName = "DataKeyName.RedstoneWireEastDataName";
        public final static String RedstoneWireNorthDataName = "DataKeyName.RedstoneWireNorthDataName";
        public final static String RedstoneWireSouthDataName = "DataKeyName.RedstoneWireSouthDataName";
        public final static String RedstoneWireWestDataName = "DataKeyName.RedstoneWireWestDataName";

        public final static String RepeaterDelayDataName = "DataKeyName.RepeaterDelayDataName";
        public final static String RepeaterLockedDataName = "DataKeyName.RepeaterLockedDataName";
        public final static String RespawnAnchorDataName = "DataKeyName.RespawnAnchorDataName";
        public final static String RotatableDataName = "DataKeyName.RotatableDataName";
        public final static String SaplingDataName = "DataKeyName.SaplingDataName";
        public final static String ScaffoldingBottomDataName = "DataKeyName.ScaffoldingBottomDataName";
        public final static String ScaffoldingDistanceDataName = "DataKeyName.ScaffoldingDistanceDataName";
        public final static String SculkCatalystDataName = "DataKeyName.SculkCatalystDataName";
        public final static String SculkSensorDataName = "DataKeyName.SculkSensorDataName";
        public final static String SculkShriekerCanSummonDataName = "DataKeyName.SculkShriekerCanSummonDataName";
        public final static String SculkShriekerShriekingDataName = "DataKeyName.SculkShriekerShriekingDataName";
        public final static String SeaPickleDataName = "DataKeyName.SeaPickleDataName";
        public final static String SlabDataName = "DataKeyName.SlabDataName";
        public final static String SnowableDataName = "DataKeyName.SnowableDataName";
        public final static String SnowDataName = "DataKeyName.SnowDataName";
        public final static String StairsDataName = "DataKeyName.StairsDataName";
        public final static String StructureBlockDataName = "DataKeyName.StructureBlockDataName";
        public final static String TechnicalPistonDataName = "DataKeyName.TechnicalPistonDataName";
        public final static String TNTDataName = "DataKeyName.TNTDataName";
        public final static String TrialSpawnerDataName = "DataKeyName.TrialSpawnerDataName";
        public final static String TripwireDataName = "DataKeyName.TripwireDataName";
        public final static String TurtleEggDataName = "DataKeyName.TurtleEggDataName";
        public final static String WallHeightEastDataName = "DataKeyName.WallHeightEastDataName";
        public final static String WallHeightNorthDataName = "DataKeyName.WallHeightNorthDataName";
        public final static String WallHeightSouthDataName = "DataKeyName.WallHeightSouthDataName";
        public final static String WallHeightWestDataName = "DataKeyName.WallHeightWestDataName";
        public final static String WallUpDataName = "DataKeyName.WallUpDataName";
        public final static String WaterloggedDataName = "DataKeyName.WaterloggedDataName";

    }
}
