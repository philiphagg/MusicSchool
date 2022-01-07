package soundgood.model;

public class LeaseException extends Throwable{
        /**
         * Create a new instance thrown because of the specified reason.
         *
         * @param reason Why the exception was thrown.
         */
        public LeaseException(String reason) {
            super(reason);
        }

        /**
         * Create a new instance thrown because of the specified reason and exception.
         *
         * @param reason    Why the exception was thrown.
         * @param rootCause The exception that caused this exception to be thrown.
         */
        public LeaseException(String reason, Throwable rootCause) {
            super(reason, rootCause);
        }
    }
