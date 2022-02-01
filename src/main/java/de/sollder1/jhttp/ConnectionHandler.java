package de.sollder1.jhttp;

import de.sollder1.jhttp.request.HttpRequest;
import de.sollder1.jhttp.request.HttpRequestParser;
import de.sollder1.jhttp.response.DefaultResponses;
import de.sollder1.jhttp.response.HttpResponse;
import de.sollder1.jhttp.response.HttpStatus;
import de.sollder1.jhttp.response.RequestHandler;

import java.io.*;
import java.net.Socket;

public class ConnectionHandler implements Runnable {

    private final Socket clientSocket;

    public ConnectionHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {

        try {

            var inputStream = clientSocket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            HttpRequest request = null;
            HttpResponse response = null;

            try {
                request = HttpRequestParser.parse(bufferedReader);
                response = RequestHandler.handle(request);
            } catch (Throwable throwable) {
                response = DefaultResponses.getServerError();
            }

            writeResponseAndClose(response);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeResponseAndClose(HttpResponse response) throws IOException {
        var bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        bufferedWriter.write(response.serialise());
        bufferedWriter.flush();
        clientSocket.close();

    }
}
