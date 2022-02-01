package de.sollder1.jhttp.config;

public class ConfigurationPojo {

    private int port = 8000;
    private int executorThreads = 4;
    private int defaultBufferSize = 2;
    private String basePath = "/var/jhttp/";

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getExecutorThreads() {
        return executorThreads;
    }

    public void setExecutorThreads(int executorThreads) {
        this.executorThreads = executorThreads;
    }

    public int getDefaultBufferSize() {
        return defaultBufferSize;
    }

    public void setDefaultBufferSize(int defaultBufferSize) {
        this.defaultBufferSize = defaultBufferSize;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }
}
