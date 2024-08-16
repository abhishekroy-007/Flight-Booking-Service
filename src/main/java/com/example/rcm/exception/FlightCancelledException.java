package com.example.rcm.exception;

public class FlightCancelledException extends RuntimeException {
    public FlightCancelledException(String message) {
        super(message);
    }
}
