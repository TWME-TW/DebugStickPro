package dev.twme.debugstickpro.configs;

public class LangFile {
    public class ActionBar {
        public static String SelectedDataFormat = "<b><u>%data%</u></b>";
        public static String formatSelectedData(String data,boolean isUsing){
            if(isUsing)
                return data;
            return SelectedDataFormat.replace("%data%", data);
        }
    }
    public static String Ageable = "Age: %data%";
    public static String AnaloguePowerable = "Power: %data%";
    public static String Attachable = "Attached: %data%";
    public static String Bamboo = "Leaves: %data%";
    public static String Bed = "Part: %data%";
    public static String Beehive = "Honey Level: %data%";
    public static String Bell = "Attachment: %data%";
    public static String BigDripleaf = "Tilt: %data%";
    public static String Bisected = "Half: %data%";
    public static String BrewingStandBottle_0 = "Bottle 0: %data%";
    public static String BrewingStandBottle_1 = "Bottle 1: %data%";
    public static String BrewingStandBottle_2 = "Bottle 2: %data%";
    public static String Brushable = "Brushed: %data%";
}
