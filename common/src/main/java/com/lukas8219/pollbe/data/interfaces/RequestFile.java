package com.lukas8219.pollbe.data.interfaces;

public interface RequestFile {

    byte[] getBytes();

    String getOriginalFileName();

    String getExtension();
}
