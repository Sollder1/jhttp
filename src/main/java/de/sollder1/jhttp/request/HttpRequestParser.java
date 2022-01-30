package de.sollder1.jhttp.request;

import de.sollder1.jhttp.config.ConfigManager;
import org.tinylog.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HttpRequestParser {

    private static final Pattern linePattern = Pattern.compile("\r\n");
    private static final Pattern blankPattern = Pattern.compile(" ");
    private static final Pattern headerPattern = Pattern.compile(": ");

    public static HttpRequest parse(BufferedReader reader) throws IOException {

        var lines = linePattern.split(read(reader));

        HttpRequest request = new HttpRequest();

        for (int i = 0; i < lines.length; i++) {
            if (i == 0) {
                var rawParts = blankPattern.split(lines[i]);
                request.setMethod(HttpMethod.valueOf(rawParts[0]));
                request.setPath(rawParts[1]);
                request.setHttpVersion(rawParts[2]);
            } else if (i == lines.length - 1) {
                request.setPayload(lines[lines.length - 1]);
            } else if (i == lines.length - 2) {
                //Nothing... seperates paylaod and rest of the request...
            } else {
                var rawParts = headerPattern.split(lines[i]);
                request.addHeader(rawParts[0], rawParts[1]);
            }
        }

        return request;

    }

    private static String read(BufferedReader reader) throws IOException {

        int bufferSize = ConfigManager.get().getDefaultBufferSize();
        int alreadyRead = 0;
        char[] buffer = new char[bufferSize];

        while (true) {

            var bufferSpaceLeft = bufferSize - alreadyRead;
            var readBytes = reader.read(buffer, alreadyRead, bufferSpaceLeft);
            alreadyRead += readBytes;

            if (readBytes == bufferSpaceLeft) {
                bufferSize *= 2;
                buffer = Arrays.copyOf(buffer, bufferSize);
            } else {
                break;
            }

        }

        return new String(buffer);

    }


}
