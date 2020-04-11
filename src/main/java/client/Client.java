package client;

import client.sender.Sender;

import java.io.IOException;

public class Client {

    public static void main(String[] args) {
        Sender sender = new Sender();
        try {
            sender.start();
            System.out.println("Started");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}