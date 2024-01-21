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
    public static String Ageable = "Age: %age%";
    public static String AnaloguePowerable = "Power: %power%";
}
