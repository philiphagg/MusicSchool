package soundgood.integration;

public class SgDBException extends Exception {

    public SgDBException(String reason) {
        super(reason);
    }

    public SgDBException(String reason, Throwable rootCause) {
        super(reason, rootCause);
    }
}
