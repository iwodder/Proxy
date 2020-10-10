package com.wodder;

import java.nio.file.FileSystem;

public class FileProxy implements com.wodder.File {
  private static int ctr = 0;

  private RealFile realFile;
  private final StringBuffer buffer;

  public FileProxy(RealFile file) {
    this.realFile = file;
     buffer = new StringBuffer();
  }

  public FileProxy(String path) {
    this.realFile = new RealFile(path);
    buffer = new StringBuffer();
  }

  @Override
  public String read() {
    if (buffer.length() == 0) {
      ctr++;
      this.buffer.append(realFile.read());
    }
    return buffer.toString();
  }

  @Override
  public void write(String data) {
    this.realFile = new RealFile(createPathCopy());
    this.buffer.append(data);
    this.realFile.write(buffer.toString());
  }

  private String createPathCopy() {
    String path = this.realFile.getPath();
    int i = path.lastIndexOf(java.io.File.separator) + 1;
    String oldName = path.substring(i);
    return path.replace(oldName, "copy_"+oldName);
  }

  @Override
  public int getCtr() {
    return ctr;
  }

  @Override
  public boolean isDirectory() {
    return realFile.isDirectory();
  }

  @Override
  public String getPath() {
    return realFile.getPath();
  }
}
