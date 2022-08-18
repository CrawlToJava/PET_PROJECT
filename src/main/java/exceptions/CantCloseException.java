package exceptions;

public class CantCloseException extends RuntimeException{
    public CantCloseException(String message) {
        System.out.println(message);
    }
}
