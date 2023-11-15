package com.google.api.client.extensions.servlet.auth.oauth2;

import com.google.api.client.util.store.FileDataStoreFactory;

import java.io.IOException;

public class Utils {
    public static FileDataStoreFactory getDataSourceFactory(){
//        java.io.File DATA_STORE_DIR = new java.io.File("/Users/xiaohaohong/Desktop/gcredentials/gplus-web-servlet");
        java.io.File DATA_STORE_DIR = new java.io.File("C:/Users/trick/gcredentials/gplus-web-servlet");
        FileDataStoreFactory dataStoreFactory = null;
        try {
            dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataStoreFactory;
    }
}
