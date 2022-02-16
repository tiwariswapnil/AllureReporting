package com.github.util;

import java.io.File;
import java.io.FileInputStream;

public class Properties {
    protected static File file = null;
    protected static FileInputStream configFis;
    protected static java.util.Properties configProp;

    public static String getProperty(String prop) {
        try {
            file = new File("config" + File.separator + "config.properties");
            configFis = new FileInputStream(file.getAbsolutePath());
            configProp = new java.util.Properties();
            configProp.load(configFis);
            String value = configProp.getProperty(prop);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getProperty(String property, String filename) {
        try {
            file = new File("config" + File.separator + filename);
            configFis = new FileInputStream(file.getAbsolutePath());
            configProp = new java.util.Properties();
            configProp.load(configFis);
            String value = configProp.getProperty(property);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
