package soundgood.model;

/**
 * exception if something goes wrong with the instrument
 */
public class InstrumentException extends Throwable {

    public InstrumentException(String reason) {
        super(reason);
    }


    public InstrumentException(String reason, Throwable rootCause) {
        super(reason, rootCause);
    }
}
