package tn.esprit.spring.kaddem.services;

public class DepartementNotFoundException extends RuntimeException {
    public DepartementNotFoundException(String message) {
        super(message);
    }

    public DepartementNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
