package de.sollder1.jhttp.response;

public enum HttpStatus {

    OK(200, "Ok"),
    METHOD_NOT_ALLOWED(415, "Method not allowed"),
    NOT_FOUND(404, "Page not found"),
    SERVER_ERROR(500, "Servererror, nothing you can do!");

    private final int code;
    private final String message;

    HttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
