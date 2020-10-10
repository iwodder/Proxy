package com.wodder;

public interface File {
    String read();

    void write(String data);

    int getCtr();

    boolean isDirectory();

    String getPath();
}
