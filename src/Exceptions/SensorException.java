package Exceptions;

    /**
     * Used if a non-existing sensor is called
     */

    public class SensorException extends Exception {
        public SensorException() {super (); }
        public SensorException(String message) {super (message); }
        public SensorException(String message, Throwable t) {super (message, t); }

    }

