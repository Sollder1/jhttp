package de.sollder1.jhttp.response;

import de.sollder1.jhttp.request.HttpMethod;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {

    private static final String LINE_SEPARATOR = "\r\n";

    private HttpStatus status;
    private String httpVersion = "HTTP/1.1";
    private Map<String, String> headers = new HashMap<>();
    private String payload;

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String serialise() {
        StringBuilder builder = new StringBuilder();
        builder.append(httpVersion).append(" ").append(status.getCode()).append(" ")
                .append(status.getMessage()).append(LINE_SEPARATOR);

        headers.forEach((key, value) -> builder.append(key).append(": ")
                .append(value).append(LINE_SEPARATOR));

        builder.append(LINE_SEPARATOR);

        if (payload != null) {
            builder.append(payload);
        }

        return builder.toString();
    }

}
