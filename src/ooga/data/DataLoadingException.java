package ooga.data;

public class DataLoadingException extends Exception {

  public DataLoadingException(String message, Throwable cause) {
    super(message, cause);
  }

  public DataLoadingException(String message) {
    super(message);
  }
}
