package com.woof.util;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppLogger {
    private static final Logger logger = Logger.getLogger(AppLogger.class.getName());

    static {
        try {
            // 設定log檔案的存放路徑
            String logFilePath = "/logs/logfile.log";

            File logDirectory = new File("/logs/");
            if (!logDirectory.exists()) {
                logDirectory.mkdirs(); // 創建目錄及其父目錄
            }

            // 創建FileHandler，將log寫入指定檔案
            FileHandler fileHandler = new FileHandler(logFilePath, true);
            fileHandler.setFormatter(new SimpleFormatter());

            // 將FileHandler添加到Logger
            logger.addHandler(fileHandler);

            // 設定log的level
            logger.setLevel(Level.ALL);
        } catch (Exception e) {
            e.printStackTrace(); // 處理初始化logger的異常
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}

