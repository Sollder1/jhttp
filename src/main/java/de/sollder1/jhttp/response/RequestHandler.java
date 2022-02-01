package de.sollder1.jhttp.response;

import de.sollder1.jhttp.config.ConfigManager;
import de.sollder1.jhttp.request.HttpRequest;
import org.tinylog.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class RequestHandler {


    public static HttpResponse handle(HttpRequest request) throws IOException {

        Path requestPath = Path.of(ConfigManager.get().getBasePath(), resolvePathAliase(request.getPath()));

        if (Files.notExists(requestPath) || Files.isDirectory(requestPath)) {
            return DefaultResponses.getNotFoundError();
        }

        Logger.info("Serving requestpath: {}", request.getPath());

        HttpResponse response = new HttpResponse();
        String fileContent = Files.readString(requestPath);
        response.setPayload(fileContent);
        response.setStatus(HttpStatus.OK);
        DefaultResponses.putBaseHeaders(response);

        return response;

    }

    private static String resolvePathAliase(String path) {
        //TODO: Konfigurierbar machen...
        if (Objects.equals(path, "/")) {
            return "index.html";
        }
        return path;
    }


}
