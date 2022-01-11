package soundgood.integration;

/**
 * exception that throws upon errors with
 * database communication
 */
public class SgDBException extends Exception {

    public SgDBException(String reason) {
        super(reason);
    }

    public SgDBException(String reason, Throwable rootCause) {
        super(reason, rootCause);
    }
}
