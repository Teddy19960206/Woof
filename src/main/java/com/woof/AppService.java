package com.woof;

public interface AppService<T> {
    byte[] getPhotoById(T no);
}
