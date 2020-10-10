package com.wodder;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RealFile implements com.wodder.File {

  private final String path;
  private final java.io.File file;
  private final StringBuffer stringBuffer;
  private boolean read = true;

  public RealFile(String path) {
    this.path = path;
    this.file = new java.io.File(path);
    stringBuffer = new StringBuffer();
  }

  @Override
  public String read() {
    if (read) {
      byte[] buff = new byte[1024];
      try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
        while (true) {
          int i = bis.read(buff);
          if (i == -1) {
            break;
          }
          stringBuffer.append(new String(buff,0,i, StandardCharsets.UTF_8));
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      read = false;
    }
    return stringBuffer.toString();
  }

  @Override
  public void write(String data) {
    this.stringBuffer.append(data);
    try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file,false))) {
      bos.write(stringBuffer.toString().getBytes(StandardCharsets.UTF_8));
      bos.flush();
      read = false;
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public int getCtr() {
    return 0;
  }

  @Override
  public boolean isDirectory() {
    return file.isDirectory();
  }

  @Override
  public String getPath() {
    return this.path;
  }
}
