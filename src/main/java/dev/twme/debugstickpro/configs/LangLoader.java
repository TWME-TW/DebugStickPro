package dev.twme.debugstickpro.configs;

import dev.twme.debugstickpro.DebugStickPro;
import dev.twme.debugstickpro.util.Log;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LangLoader {
    private final static LangLoader instance = new LangLoader();
    private File file;
    private YamlConfiguration langFile;
    private LangLoader() {
    }

    public void load(){
        file = new File(DebugStickPro.getInstance().getDataFolder(), "lang.yml");

        if (!file.exists()) {
            DebugStickPro.getInstance().saveResource("lang.yml", false);
        }

        langFile = new YamlConfiguration();
        langFile.options().parseComments(true);

        try {
            langFile.load(file);
        } catch (Exception e) {
            Log.warning(e.getMessage());
        }

        LangFile.LangFileVersion = langFile.getInt("LangFileVersion");

        if(!checkLangFileVersion()){
            load();
            return;
        }
        loadValues();
    }

    public boolean checkLangFileVersion(){
        if(LangFile.LangFileVersion != DebugStickPro.LangVersion){
            Log.warning("Lang file version is not compatible with this version of the plugin.");
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String strDate = formatter.format(date);
            String backupFileName = file.getAbsolutePath().replace("lang","lang-" + strDate);

            File newFile = new File(backupFileName);
            if (file.renameTo(newFile)) {
                Log.warning("Old lang file has been backed up to " + newFile.getName());
            } else {
                Log.warning("Failed to backed up old lang file to " + newFile.getName());
            }
            return false;
        }
        return true;
    }

    public void save() {
        try {
            langFile.save(file);
        } catch (Exception e) {
            Log.warning(e.getMessage());
        }
    }

    public void set(String path, Object value) {
        langFile.set(path, value);
        save();
        loadValues();
    }

    public static LangLoader getInstance() {
        return instance;
    }

    private void loadValues() {

        LangFile.CommandsMessages.Help.Title = langFile.getString("CommandsMessages.Help.Title");
        LangFile.CommandsMessages.Help.Description = langFile.getString("CommandsMessages.Help.Description");
        LangFile.CommandsMessages.Help.Usage = langFile.getString("CommandsMessages.Help.Usage");

        LangFile.CommandsMessages.Reload.Success = langFile.getString("CommandsMessages.Reload.Success");

        LangFile.CommandsMessages.Give.NoPlayer = langFile.getString("CommandsMessages.Give.NoPlayer");
        LangFile.CommandsMessages.Give.Success = langFile.getString("CommandsMessages.Give.Success");

        LangFile.CommandsMessages.Mode.Usage = langFile.getString("CommandsMessages.Mode.Usage");
        LangFile.CommandsMessages.Mode.SuccessSetToClassic = langFile.getString("CommandsMessages.Mode.SuccessSetToClassic");
        LangFile.CommandsMessages.Mode.SuccessSetToCopy = langFile.getString("CommandsMessages.Mode.SuccessSetToCopy");
        LangFile.CommandsMessages.Mode.SuccessSetToFreeze = langFile.getString("CommandsMessages.Mode.SuccessSetToFreeze");

        LangFile.CommandsMessages.NoPermission = langFile.getString("CommandsMessages.NoPermission");

        LangFile.CommandsMessages.YouAreNotPlayer = langFile.getString("CommandsMessages.YouAreNotPlayer");

        LangFile.ActionBar.SelectedDataFormat = langFile.getString("ActionBar.SelectedDataFormat");
        LangFile.ActionBar.NotSelectedDataFormat = langFile.getString("ActionBar.NotSelectedDataFormat");
        LangFile.ActionBar.CopiedBlockDataFormat = langFile.getString("ActionBar.CopiedBlockDataFormat");

        LangFile.Tips.classicModeIntroduction = langFile.getString("Tips.classicModeIntroduction");
        LangFile.Tips.copyModeIntroduction = langFile.getString("Tips.copyModeIntroduction");
        LangFile.Tips.freezeModeIntroduction = langFile.getString("Tips.freezeModeIntroduction");

        LangFile.DataKeyName.AgeableDataName = langFile.getString("DataKeyName.AgeableDataName");
        LangFile.DataKeyName.AnaloguePowerableDataName = langFile.getString("DataKeyName.AnaloguePowerableDataName");
        LangFile.DataKeyName.AttachableDataName = langFile.getString("DataKeyName.AttachableDataName");
        LangFile.DataKeyName.BambooDataName = langFile.getString("DataKeyName.BambooDataName");
        LangFile.DataKeyName.BedDataName = langFile.getString("DataKeyName.BedDataName");
        LangFile.DataKeyName.BeehiveDataName = langFile.getString("DataKeyName.BeehiveDataName");
        LangFile.DataKeyName.BellDataName = langFile.getString("DataKeyName.BellDataName");
        LangFile.DataKeyName.BigDripleafDataName = langFile.getString("DataKeyName.BigDripleafDataName");
        LangFile.DataKeyName.BisectedDataName = langFile.getString("DataKeyName.BisectedDataName");
        LangFile.DataKeyName.BrewingStandBottle_0DataName = langFile.getString("DataKeyName.BrewingStandBottle_0DataName");
        LangFile.DataKeyName.BrewingStandBottle_1DataName = langFile.getString("DataKeyName.BrewingStandBottle_1DataName");
        LangFile.DataKeyName.BrewingStandBottle_2DataName = langFile.getString("DataKeyName.BrewingStandBottle_2DataName");
        LangFile.DataKeyName.BrushableDataName = langFile.getString("DataKeyName.BrushableDataName");
        LangFile.DataKeyName.BubbleColumnDataName = langFile.getString("DataKeyName.BubbleColumnDataName");
        LangFile.DataKeyName.CakeDataName = langFile.getString("DataKeyName.CakeDataName");
        LangFile.DataKeyName.CampfireDataName = langFile.getString("DataKeyName.CampfireDataName");
        LangFile.DataKeyName.CandleDataName = langFile.getString("DataKeyName.CandleDataName");
        LangFile.DataKeyName.CaveVinesPlantDataName = langFile.getString("DataKeyName.CaveVinesPlantDataName");
        LangFile.DataKeyName.ChestDataName = langFile.getString("DataKeyName.ChestDataName");
        LangFile.DataKeyName.ChiseledBookshelfSlot_0DataName = langFile.getString("DataKeyName.ChiseledBookshelfSlot_0DataName");
        LangFile.DataKeyName.ChiseledBookshelfSlot_1DataName = langFile.getString("DataKeyName.ChiseledBookshelfSlot_1DataName");
        LangFile.DataKeyName.ChiseledBookshelfSlot_2DataName = langFile.getString("DataKeyName.ChiseledBookshelfSlot_2DataName");
        LangFile.DataKeyName.ChiseledBookshelfSlot_3DataName = langFile.getString("DataKeyName.ChiseledBookshelfSlot_3DataName");
        LangFile.DataKeyName.ChiseledBookshelfSlot_4DataName = langFile.getString("DataKeyName.ChiseledBookshelfSlot_4DataName");
        LangFile.DataKeyName.ChiseledBookshelfSlot_5DataName = langFile.getString("DataKeyName.ChiseledBookshelfSlot_5DataName");
        LangFile.DataKeyName.CommandBlockDataName = langFile.getString("DataKeyName.CommandBlockDataName");
        LangFile.DataKeyName.ComparatorDataName = langFile.getString("DataKeyName.ComparatorDataName");
        LangFile.DataKeyName.CrafterCraftingDataName = langFile.getString("DataKeyName.CrafterCraftingDataName");
        LangFile.DataKeyName.CrafterOrientationDataName = langFile.getString("DataKeyName.CrafterOrientationDataName");
        LangFile.DataKeyName.CrafterTriggerDataName = langFile.getString("DataKeyName.CrafterTriggerDataName");
        LangFile.DataKeyName.DaylightDetectorDataName = langFile.getString("DataKeyName.DaylightDetectorDataName");
        LangFile.DataKeyName.DirectionalDataName = langFile.getString("DataKeyName.DirectionalDataName");
        LangFile.DataKeyName.DispenserDataName = langFile.getString("DataKeyName.DispenserDataName");
        LangFile.DataKeyName.DoorDataName = langFile.getString("DataKeyName.DoorDataName");
        LangFile.DataKeyName.EndPortalFrameDataName = langFile.getString("DataKeyName.EndPortalFrameDataName");
        LangFile.DataKeyName.FaceAttachableDataName = langFile.getString("DataKeyName.FaceAttachableDataName");
        LangFile.DataKeyName.FarmlandDataName = langFile.getString("DataKeyName.FarmlandDataName");
        LangFile.DataKeyName.GateDataName = langFile.getString("DataKeyName.GateDataName");
        LangFile.DataKeyName.HangableDataName = langFile.getString("DataKeyName.HangableDataName");
        LangFile.DataKeyName.HatchableDataName = langFile.getString("DataKeyName.HatchableDataName");
        LangFile.DataKeyName.HopperDataName = langFile.getString("DataKeyName.HopperDataName");
        LangFile.DataKeyName.JigsawDataName = langFile.getString("DataKeyName.JigsawDataName");
        LangFile.DataKeyName.JukeboxDataName = langFile.getString("DataKeyName.JukeboxDataName");
        LangFile.DataKeyName.LeavesDistanceDataName = langFile.getString("DataKeyName.LeavesDistanceDataName");
        LangFile.DataKeyName.LeavesPersistentDataName = langFile.getString("DataKeyName.LeavesPersistentDataName");
        LangFile.DataKeyName.LectronDataName = langFile.getString("DataKeyName.LectronDataName");
        LangFile.DataKeyName.LevelledDataName = langFile.getString("DataKeyName.LevelledDataName");
        LangFile.DataKeyName.LightableDataName = langFile.getString("DataKeyName.LightableDataName");
        LangFile.DataKeyName.MultipleFacingDownDataName = langFile.getString("DataKeyName.MultipleFacingDownDataName");
        LangFile.DataKeyName.MultipleFacingEastDataName = langFile.getString("DataKeyName.MultipleFacingEastDataName");
        LangFile.DataKeyName.MultipleFacingEastNorthEastDataName = langFile.getString("DataKeyName.MultipleFacingEastNorthEastDataName");
        LangFile.DataKeyName.MultipleFacingEastSouthEastDataName = langFile.getString("DataKeyName.MultipleFacingEastSouthEastDataName");
        LangFile.DataKeyName.MultipleFacingNorthDataName = langFile.getString("DataKeyName.MultipleFacingNorthDataName");
        LangFile.DataKeyName.MultipleFacingNorthEastDataName = langFile.getString("DataKeyName.MultipleFacingNorthEastDataName");
        LangFile.DataKeyName.MultipleFacingNorthNorthEastDataName = langFile.getString("DataKeyName.MultipleFacingNorthNorthEastDataName");
        LangFile.DataKeyName.MultipleFacingNorthNorthWestDataName = langFile.getString("DataKeyName.MultipleFacingNorthNorthWestDataName");
        LangFile.DataKeyName.MultipleFacingNorthWestDataName = langFile.getString("DataKeyName.MultipleFacingNorthWestDataName");
        LangFile.DataKeyName.MultipleFacingSelfDataName = langFile.getString("DataKeyName.MultipleFacingSelfDataName");
        LangFile.DataKeyName.MultipleFacingSouthDataName = langFile.getString("DataKeyName.MultipleFacingSouthDataName");
        LangFile.DataKeyName.MultipleFacingSouthEastDataName = langFile.getString("DataKeyName.MultipleFacingSouthEastDataName");
        LangFile.DataKeyName.MultipleFacingSouthSouthEastDataName = langFile.getString("DataKeyName.MultipleFacingSouthSouthEastDataName");
        LangFile.DataKeyName.MultipleFacingSouthSouthWestDataName = langFile.getString("DataKeyName.MultipleFacingSouthSouthWestDataName");
        LangFile.DataKeyName.MultipleFacingSouthWestDataName = langFile.getString("DataKeyName.MultipleFacingSouthWestDataName");
        LangFile.DataKeyName.MultipleFacingUpDataName = langFile.getString("DataKeyName.MultipleFacingUpDataName");
        LangFile.DataKeyName.MultipleFacingWestDataName = langFile.getString("DataKeyName.MultipleFacingWestDataName");
        LangFile.DataKeyName.MultipleFacingWestNorthWestDataName = langFile.getString("DataKeyName.MultipleFacingWestNorthWestDataName");
        LangFile.DataKeyName.MultipleFacingWestSouthWestDataName = langFile.getString("DataKeyName.MultipleFacingWestSouthWestDataName");
        LangFile.DataKeyName.NoteBlockInstrumentDataName = langFile.getString("DataKeyName.NoteBlockInstrumentDataName");
        LangFile.DataKeyName.NoteBlockNoteDataName = langFile.getString("DataKeyName.NoteBlockNoteDataName");
        LangFile.DataKeyName.OrientableDataName = langFile.getString("DataKeyName.OrientableDataName");
        LangFile.DataKeyName.PinkPetalsDataname = langFile.getString("DataKeyName.PinkPetalsDataname");
        LangFile.DataKeyName.PistonDataName = langFile.getString("DataKeyName.PistonDataName");
        LangFile.DataKeyName.PistonHeadDataName = langFile.getString("DataKeyName.PistonHeadDataName");
        LangFile.DataKeyName.PointedDripstoneThicknessDataName = langFile.getString("DataKeyName.PointedDripstoneThicknessDataName");
        LangFile.DataKeyName.PointedDripstoneVerticalDirectionDataName = langFile.getString("DataKeyName.PointedDripstoneVerticalDirectionDataName");
        LangFile.DataKeyName.PowerableDataName = langFile.getString("DataKeyName.PowerableDataName");
        LangFile.DataKeyName.RailDataName = langFile.getString("DataKeyName.RailDataName");
        LangFile.DataKeyName.RedstoneWireEastDataName = langFile.getString("DataKeyName.RedstoneWireEastDataName");
        LangFile.DataKeyName.RedstoneWireNorthDataName = langFile.getString("DataKeyName.RedstoneWireNorthDataName");
        LangFile.DataKeyName.RedstoneWireSouthDataName = langFile.getString("DataKeyName.RedstoneWireSouthDataName");
        LangFile.DataKeyName.RedstoneWireWestDataName = langFile.getString("DataKeyName.RedstoneWireWestDataName");
        LangFile.DataKeyName.RepeaterDelayDataName = langFile.getString("DataKeyName.RepeaterDelayDataName");
        LangFile.DataKeyName.RepeaterLockedDataName = langFile.getString("DataKeyName.RepeaterLockedDataName");
        LangFile.DataKeyName.RespawnAnchorDataName = langFile.getString("DataKeyName.RespawnAnchorDataName");
        LangFile.DataKeyName.RotatableDataName = langFile.getString("DataKeyName.RotatableDataName");
        LangFile.DataKeyName.SaplingDataName = langFile.getString("DataKeyName.SaplingDataName");
        LangFile.DataKeyName.ScaffoldingBottomDataName = langFile.getString("DataKeyName.ScaffoldingBottomDataName");
        LangFile.DataKeyName.ScaffoldingDistanceDataName = langFile.getString("DataKeyName.ScaffoldingDistanceDataName");
        LangFile.DataKeyName.SculkCatalystDataName = langFile.getString("DataKeyName.SculkCatalystDataName");
        LangFile.DataKeyName.SculkSensorDataName = langFile.getString("DataKeyName.SculkSensorDataName");
        LangFile.DataKeyName.SculkShriekerCanSummonDataName = langFile.getString("DataKeyName.SculkShriekerCanSummonDataName");
        LangFile.DataKeyName.SculkShriekerShriekingDataName = langFile.getString("DataKeyName.SculkShriekerShriekingDataName");
        LangFile.DataKeyName.SeaPickleDataName = langFile.getString("DataKeyName.SeaPickleDataName");
        LangFile.DataKeyName.SlabDataName = langFile.getString("DataKeyName.SlabDataName");
        LangFile.DataKeyName.SnowableDataName = langFile.getString("DataKeyName.SnowableDataName");
        LangFile.DataKeyName.SnowDataName = langFile.getString("DataKeyName.SnowDataName");
        LangFile.DataKeyName.StairsDataName = langFile.getString("DataKeyName.StairsDataName");
        LangFile.DataKeyName.StructureBlockDataName = langFile.getString("DataKeyName.StructureBlockDataName");
        LangFile.DataKeyName.TechnicalPistonDataName = langFile.getString("DataKeyName.TechnicalPistonDataName");
        LangFile.DataKeyName.TNTDataName = langFile.getString("DataKeyName.TNTDataName");
        LangFile.DataKeyName.TrialSpawnerDataName = langFile.getString("DataKeyName.TrialSpawnerDataName");
        LangFile.DataKeyName.TripwireDataName = langFile.getString("DataKeyName.TripwireDataName");
        LangFile.DataKeyName.TurtleEggDataName = langFile.getString("DataKeyName.TurtleEggDataName");
        LangFile.DataKeyName.WallHeightEastDataName = langFile.getString("DataKeyName.WallHeightEastDataName");
        LangFile.DataKeyName.WallHeightNorthDataName = langFile.getString("DataKeyName.WallHeightNorthDataName");
        LangFile.DataKeyName.WallHeightSouthDataName = langFile.getString("DataKeyName.WallHeightSouthDataName");
        LangFile.DataKeyName.WallHeightWestDataName = langFile.getString("DataKeyName.WallHeightWestDataName");
        LangFile.DataKeyName.WallUpDataName = langFile.getString("DataKeyName.WallUpDataName");
        LangFile.DataKeyName.WaterloggedDataName = langFile.getString("DataKeyName.WaterloggedDataName");

    }
}
