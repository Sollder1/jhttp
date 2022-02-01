package de.sollder1.jhttp.response;

import java.util.HashMap;
import java.util.Map;

public final class DefaultResponses {

    private DefaultResponses() {
    }

    public static HttpResponse getServerError() {
        HttpResponse response = new HttpResponse();
        response.setStatus(HttpStatus.SERVER_ERROR);
        putBaseHeaders(response);
        response.setPayload("<h1>500 - Server Error</h1> <p>Nothing you can do I am afraid!</p>");
        return response;
    }

    public static HttpResponse getNotFoundError() {
        HttpResponse response = new HttpResponse();
        response.setStatus(HttpStatus.NOT_FOUND);
        putBaseHeaders(response);
        response.setPayload("<h1>404 - Not Found</h1> <p>This ressource does not exist on the Server!</p>");
        return response;
    }

    public static void putBaseHeaders(HttpResponse response) {
        response.addHeader("server", "Sollders-JHttp");
     //   response.addHeader("Content-Type", "text/html");
        response.addHeader("Connection", "Closed");
    }

}
