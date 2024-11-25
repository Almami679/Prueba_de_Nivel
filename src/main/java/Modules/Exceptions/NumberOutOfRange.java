package Modules.Exceptions;

public class NumberOutOfRange extends RuntimeException {
    public NumberOutOfRange(String message) {
        super(message);
    }
}
