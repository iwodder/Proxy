package com.wodder;

public class App {

  static App app;

  public static void main(String[] args) {
    app = new App();

    System.out.println("Creating a file");
    File fileProxy1 = new FileProxy(app.getClass().getResource("/shakespeare.txt").getPath());
    File fileProxy2 = new FileProxy(app.getClass().getResource("/shakespeare.txt").getPath());

    System.out.printf("Current number of reads %d%n", fileProxy1.getCtr());
    System.out.println("Reading in the file contents...");
    String theWorks = fileProxy1.read();
    System.out.printf("Current number of reads %d%n", fileProxy1.getCtr());

    System.out.println("Reading in the file contents...");
    String theWorks2 = fileProxy1.read();
    System.out.printf("Current number of reads %d%n", fileProxy1.getCtr());

    if (theWorks.equals(theWorks2)) {
      System.out.println("Current both files are the same.");
    }

    System.out.println("I want to add my ideas to the end of this!");
    fileProxy1.write("\n My new ideas.");

    theWorks = fileProxy1.read();
    theWorks2 = fileProxy2.read();

    if (theWorks.equals(theWorks2)) {
      System.out.println("Copy-on-write proxy failed!");
    } else {
      System.out.println("Copy-on-write proxy worked!");
    }
  }
}
