package server;

import server.receiver.Receiver;

import java.io.IOException;

public class Server {

    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        try {
            receiver.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}