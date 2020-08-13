package exceptions;

public class NoTimerException extends java.lang.Exception {
    public String getMessage() {
        return "This instruction contains no timer!";
    }
}
