package client.sender;

import client.console.ConsoleReader;
import client.model.Product;
import client.model.State;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;

public class Sender implements ISender {

    private ConsoleReader consoleReader = new ConsoleReader();

    @Override
    public void start() throws IOException {
        Socket clientSocket = new Socket("localhost", 4004);
        System.out.println("Started");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        while (true) {
            System.out.println("Enter command (EDIT, SAVE, DELETE, ALL)");
            String command = reader.readLine();
            if (!(command.equals(State.ALL.name()) ||
                    command.equals(State.DELETE.name()) ||
                    command.equals(State.EDIT.name()) ||
                    command.equals(State.SAVE.name()))) {
                System.out.println("Command not available!");
                continue;
            }
            State state = State.valueOf(command);
            out.write(state.name() + "\n");
            out.flush();
            String message = in.readLine();
            if (state.equals(State.ALL)) {
                System.out.println("_________________TABLE__________________");
                JSONArray jsonArray = new JSONArray(message);
                jsonArray.iterator()
                        .forEachRemaining((object) -> {
                            JSONObject jsonObject = (JSONObject) object;
                            Product product = Product.parseJson(jsonObject);
                            System.out.println(product.toRowString());
                        });
                System.out.println("________________________________________");
            } else if (State.valueOf(message).equals(state)) {
                System.out.println("________________EDITOR__________________");
                JSONObject jsonObject = new JSONObject();
                if (state.equals(State.EDIT)) jsonObject = consoleReader.edit(reader);
                else if (state.equals(State.SAVE)) jsonObject = consoleReader.save(reader);
                else if (state.equals(State.DELETE)) jsonObject = consoleReader.delete(reader);
                out.write(jsonObject.toString() + "\n");
                out.flush();
            } else {
                System.out.println("Command not available!");
            }
        }
    }

}
