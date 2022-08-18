package exceptions;

public class NotAvailableException extends RuntimeException {
    public NotAvailableException(String message) {
        System.out.println(message);
    }
}
