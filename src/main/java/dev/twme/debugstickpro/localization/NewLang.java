package dev.twme.debugstickpro.localization;

import net.kyori.adventure.text.Component;

import java.util.ArrayList;

public class NewLang {
    public static String LangFileVersion = "LangFileVersion";

    public static class CommandsMessages {
        public static class Help {
            public static String HelpMessage = "CommandsMessages.Help.HelpMessage";
        }

        public static class Reload {
            public static String Success = "CommandsMessages.Reload.Success";
        }

        public static class Give {
            public static String NoPlayer = "CommandsMessages.Give.NoPlayer";
            public static String Success = "CommandsMessages.Give.Success";
        }

        public static class Mode {
            public static String Usage = "CommandsMessages.Mode.Usage";
            public static String SuccessSetToClassic = "CommandsMessages.Mode.SuccessSetToClassic";
            public static String SuccessSetToCopy = "CommandsMessages.Mode.SuccessSetToCopy";
            public static String SuccessSetToFreeze = "CommandsMessages.Mode.SuccessSetToFreeze";
        }

        public static String NoPermission = "CommandsMessages.NoPermission";
        public static String YouAreNotPlayer = "CommandsMessages.YouAreNotPlayer";
    }

    public static class ActionBar {
        public static String SelectedDataFormat = "ActionBar.SelectedDataFormat";

        public static String formatSelectedData(String key, String value) {
            return SelectedDataFormat.replace("%key%", key).replace("%value%", value);
        }

        public static String NotSelectedDataFormat = "ActionBar.NotSelectedDataFormat";

        public static String formatNotSelectedData(String key, String value) {
            return NotSelectedDataFormat.replace("%key%", key).replace("%value%", value);
        }

        public static String CopiedBlockDataFormat = "ActionBar.CopiedBlockDataFormat";

        public static String formatCopiedBlockData(String key, String value) {
            return CopiedBlockDataFormat.replace("%key%", key).replace("%value%", value);
        }

        public static String FreezeBlockCount = "ActionBar.FreezeBlockCount";

        public static String formatFreezeBlockCount(int count) {
            return FreezeBlockCount.replace("%count%", String.valueOf(count));
        }
    }

    public static class Tips {
        public static String classicModeIntroduction = "Tips.ClassicModeIntroduction";
        public static String copyModeIntroduction = "Tips.CopyModeIntroduction";
        public static String freezeModeIntroduction = "Tips.FreezeModeIntroduction";
    }

    public static class DataKeyName {

        public static String AgeableDataName = "DataKeyName.AgeableDataName";
        public static String AnaloguePowerableDataName = "DataKeyName.AnaloguePowerableDataName";
        public static String AttachableDataName = "DataKeyName.AttachableDataName";
        public static String BambooDataName = "DataKeyName.BambooDataName";
        public static String BedDataName = "DataKeyName.BedDataName";
        public static String BeehiveDataName = "DataKeyName.BeehiveDataName";
        public static String BellDataName = "DataKeyName.BellDataName";
        public static String BigDripleafDataName = "DataKeyName.BigDripleafDataName";
        public static String BisectedDataName = "DataKeyName.BisectedDataName";
        public static String BrewingStandBottle_0DataName = "DataKeyName.BrewingStandBottle_0DataName";
        public static String BrewingStandBottle_1DataName = "DataKeyName.BrewingStandBottle_1DataName";
        public static String BrewingStandBottle_2DataName = "DataKeyName.BrewingStandBottle_2DataName";
        public static String BrushableDataName = "DataKeyName.BrushableDataName";
        public static String BubbleColumnDataName = "DataKeyName.BubbleColumnDataName";
        public static String CakeDataName = "DataKeyName.CakeDataName";
        public static String CampfireDataName = "DataKeyName.CampfireDataName";
        public static String CandleDataName = "DataKeyName.CandleDataName";
        public static String CaveVinesPlantDataName = "DataKeyName.CaveVinesPlantDataName";
        public static String ChestDataName = "DataKeyName.ChestDataName";
        public static String ChiseledBookshelfSlot_0DataName = "DataKeyName.ChiseledBookshelfSlot_0DataName";
        public static String ChiseledBookshelfSlot_1DataName = "DataKeyName.ChiseledBookshelfSlot_1DataName";
        public static String ChiseledBookshelfSlot_2DataName = "DataKeyName.ChiseledBookshelfSlot_2DataName";
        public static String ChiseledBookshelfSlot_3DataName = "DataKeyName.ChiseledBookshelfSlot_3DataName";
        public static String ChiseledBookshelfSlot_4DataName = "DataKeyName.ChiseledBookshelfSlot_4DataName";
        public static String ChiseledBookshelfSlot_5DataName = "DataKeyName.ChiseledBookshelfSlot_5DataName";
        public static String CommandBlockDataName = "DataKeyName.CommandBlockDataName";
        public static String ComparatorDataName = "DataKeyName.ComparatorDataName";
        public static String CrafterCraftingDataName = "DataKeyName.CrafterCraftingDataName";
        public static String CrafterOrientationDataName = "DataKeyName.CrafterOrientationDataName";
        public static String CrafterTriggerDataName = "DataKeyName.CrafterTriggerDataName";
        public static String DaylightDetectorDataName = "DataKeyName.DaylightDetectorDataName";
        public static String DirectionalDataName = "DataKeyName.DirectionalDataName";
        public static String DispenserDataName = "DataKeyName.DispenserDataName";
        public static String DoorDataName = "DataKeyName.DoorDataName";
        public static String EndPortalFrameDataName = "DataKeyName.EndPortalFrameDataName";
        public static String FaceAttachableDataName = "DataKeyName.FaceAttachableDataName";
        public static String FarmlandDataName = "DataKeyName.FarmlandDataName";
        public static String GateDataName = "DataKeyName.GateDataName";
        public static String HangableDataNam = "DataKeyName.HangableDataNam";
        public static String HatchableDataName = "DataKeyName.HatchableDataName";
        public static String HopperDataName = "DataKeyName.HopperDataName";
        public static String JigsawDataName = "DataKeyName.JigsawDataName";
        public static String JukeboxDataName = "DataKeyName.JukeboxDataName";
        public static String LeavesDistanceDataName = "DataKeyName.LeavesDistanceDataName";
        public static String LeavesPersistentDataName = "DataKeyName.LeavesPersistentDataName";
        public static String LectronDataName = "DataKeyName.LectronDataName";
        public static String LevelledDataName = "DataKeyName.LevelledDataName";
        public static String LightableDataName = "DataKeyName.LightableDataName";
        public static String MultipleFacingDownDataName = "DataKeyName.MultipleFacingDownDataName";
        public static String MultipleFacingEastDataName = "DataKeyName.MultipleFacingEastDataName";
        public static String MultipleFacingEastNorthEastDataName = "DataKeyName.MultipleFacingEastNorthEastDataName";
        public static String MultipleFacingEastSouthEastDataName = "DataKeyName.MultipleFacingEastSouthEastDataName";
        public static String MultipleFacingNorthDataName = "DataKeyName.MultipleFacingNorthDataName";
        public static String MultipleFacingNorthEastDataName = "DataKeyName.MultipleFacingNorthEastDataName";
        public static String MultipleFacingNorthNorthEastDataName = "DataKeyName.MultipleFacingNorthNorthEastDataName";
        public static String MultipleFacingNorthNorthWestDataName = "DataKeyName.MultipleFacingNorthNorthWestDataName";
        public static String MultipleFacingNorthWestDataName = "DataKeyName.MultipleFacingNorthWestDataName";
        public static String MultipleFacingSelfDataName = "DataKeyName.MultipleFacingSelfDataName";
        public static String MultipleFacingSouthDataName = "DataKeyName.MultipleFacingSouthDataName";
        public static String MultipleFacingSouthEastDataName = "DataKeyName.MultipleFacingSouthEastDataName";
        public static String MultipleFacingSouthSouthEastDataName = "DataKeyName.MultipleFacingSouthSouthEastDataName";
        public static String MultipleFacingSouthSouthWestDataName = "DataKeyName.MultipleFacingSouthSouthWestDataName";
        public static String MultipleFacingSouthWestDataName = "DataKeyName.MultipleFacingSouthWestDataName";
        public static String MultipleFacingUpDataName = "DataKeyName.MultipleFacingUpDataName";
        public static String MultipleFacingWestDataName = "DataKeyName.MultipleFacingWestDataName";
        public static String MultipleFacingWestNorthWestDataName = "DataKeyName.MultipleFacingWestNorthWestDataName";
        public static String MultipleFacingWestSouthWestDataName = "DataKeyName.MultipleFacingWestSouthWestDataName";
        public static String NoteBlockInstrumentDataName = "DataKeyName.NoteBlockInstrumentDataName";
        public static String NoteBlockNoteDataName = "DataKeyName.NoteBlockNoteDataName";
        public static String OrientableDataName = "DataKeyName.OrientableDataName";
        public static String PinkPetalsDataname = "DataKeyName.PinkPetalsDataname";
        public static String PistonDataName = "DataKeyName.PistonDataName";
        public static String PistonHeadDataName = "DataKeyName.PistonHeadDataName";
        public static String PointedDripstoneThicknessDataName = "DataKeyName.PointedDripstoneThicknessDataName";
        public static String PointedDripstoneVerticalDirectionDataName = "DataKeyName.PointedDripstoneVerticalDirectionDataName";
        public static String PowerableDataName = "DataKeyName.PowerableDataName";
        public static String RailDataName = "DataKeyName.RailDataName";
        public static String RedstoneWireEastDataName = "DataKeyName.RedstoneWireEastDataName";
        public static String RedstoneWireNorthDataName = "DataKeyName.RedstoneWireNorthDataName";
        public static String RedstoneWireSouthDataName = "DataKeyName.RedstoneWireSouthDataName";
        public static String RedstoneWireWestDataName = "DataKeyName.RedstoneWireWestDataName";

        public static String RepeaterDelayDataName = "DataKeyName.RepeaterDelayDataName";
        public static String RepeaterLockedDataName = "DataKeyName.RepeaterLockedDataName";
        public static String RespawnAnchorDataName = "DataKeyName.RespawnAnchorDataName";
        public static String RotatableDataName = "DataKeyName.RotatableDataName";
        public static String SaplingDataName = "DataKeyName.SaplingDataName";
        public static String ScaffoldingBottomDataName = "DataKeyName.ScaffoldingBottomDataName";
        public static String ScaffoldingDistanceDataName = "DataKeyName.ScaffoldingDistanceDataName";
        public static String SculkCatalystDataName = "DataKeyName.SculkCatalystDataName";
        public static String SculkSensorDataName = "DataKeyName.SculkSensorDataName";
        public static String SculkShriekerCanSummonDataName = "DataKeyName.SculkShriekerCanSummonDataName";
        public static String SculkShriekerShriekingDataName = "DataKeyName.SculkShriekerShriekingDataName";
        public static String SeaPickleDataName = "DataKeyName.SeaPickleDataName";
        public static String SlabDataName = "DataKeyName.SlabDataName";
        public static String SnowableDataName = "DataKeyName.SnowableDataName";
        public static String SnowDataName = "DataKeyName.SnowDataName";
        public static String StairsDataName = "DataKeyName.StairsDataName";
        public static String StructureBlockDataName = "DataKeyName.StructureBlockDataName";
        public static String TechnicalPistonDataName = "DataKeyName.TechnicalPistonDataName";
        public static String TNTDataName = "DataKeyName.TNTDataName";
        public static String TrialSpawnerDataName = "DataKeyName.TrialSpawnerDataName";
        public static String TripwireDataName = "DataKeyName.TripwireDataName";
        public static String TurtleEggDataName = "DataKeyName.TurtleEggDataName";
        public static String WallHeightEastDataName = "DataKeyName.WallHeightEastDataName";
        public static String WallHeightNorthDataName = "DataKeyName.WallHeightNorthDataName";
        public static String WallHeightSouthDataName = "DataKeyName.WallHeightSouthDataName";
        public static String WallHeightWestDataName = "DataKeyName.WallHeightWestDataName";
        public static String WallUpDataName = "DataKeyName.WallUpDataName";
        public static String WaterloggedDataName = "DataKeyName.WaterloggedDataName";

    }
}
