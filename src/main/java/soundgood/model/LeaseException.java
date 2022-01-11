package soundgood.model;

/**
 * exception that is thrown if something goes wrong with the lease
 */
public class LeaseException extends Throwable{

        public LeaseException(String reason) {
            super(reason);
        }


        public LeaseException(String reason, Throwable rootCause) {
            super(reason, rootCause);
        }
    }
