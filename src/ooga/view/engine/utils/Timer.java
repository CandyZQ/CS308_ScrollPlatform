package ooga.view.engine.utils;

public class Timer {
  private static double TIME_BASE = (double) 1000000000L;

  public static double getTime(){
    return (double) System.nanoTime()/ TIME_BASE;
  }
}
