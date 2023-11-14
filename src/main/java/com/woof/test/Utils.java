package com.woof.test;

import com.google.api.client.util.store.FileDataStoreFactory;

import java.io.IOException;

public class Utils {
    static FileDataStoreFactory getDataSourceFactory(){
        java.io.File DATA_STORE_DIR = new java.io.File("C:\\gcredentials\\gplus-web-servlet");
        FileDataStoreFactory dataStoreFactory = null;
        try {
            dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataStoreFactory;
    }
}
