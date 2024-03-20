package dev.twme.debugstickpro.localization;

import dev.twme.debugstickpro.DebugStickPro;
import dev.twme.debugstickpro.util.Log;

import java.io.*;

public class NewLangFileManager {
    public static void initialization() {
        copyFromPluginResourceFolder("lang");
    }

    private static void copyFromPluginResourceFolder(String folderName) {
        File pluginDataFolder = DebugStickPro.getInstance().getDataFolder();
        InputStream inputStream = DebugStickPro.getInstance().getResource(folderName);
        if (inputStream != null) {
            File destinationFolder = new File(pluginDataFolder, folderName);

            if (!destinationFolder.exists()) {
                destinationFolder.mkdirs();
            }

            try {
                // 复制文件
                File[] files = new File(pluginDataFolder, folderName).listFiles();
                for (File file : files) {
                    InputStream in = DebugStickPro.getInstance().getResource(folderName + "/" + file.getName());
                    OutputStream out = new FileOutputStream(new File(destinationFolder, file.getName()));
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = in.read(buffer)) > 0) {
                        out.write(buffer, 0, length);
                    }
                    in.close();
                    out.close();
                }
                Log.info("文件夹已成功复制到插件数据文件夹！");
            } catch (IOException e) {
                Log.warning("复制文件夹时出现错误：" + e.getMessage());
            }
        } else {
            Log.warning("无法找到资源文件夹：" + folderName);
        }
    }
}
