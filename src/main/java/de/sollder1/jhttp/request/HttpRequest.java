package de.sollder1.jhttp.request;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    private String method;
    private String path;
    private String httpVersion;
    private Map<String, String> headers = new HashMap<>();
    private String payload;

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
}
