package de.sollder1.jhttp;

import de.sollder1.jhttp.request.HttpRequestParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            var request = HttpRequestParser.parse(bufferedReader);
            inputStream.close();


            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
