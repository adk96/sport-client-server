package server.receiver;

import org.json.JSONArray;
import org.json.JSONObject;
import server.db.DAO;
import server.model.Product;
import server.model.State;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;

public class Receiver implements IReceiver {

    private State globalState = State.WAIT;
    private DAO dao = new DAO();

    @Override
    public void start() throws IOException {
        ServerSocket server = new ServerSocket(4004);
        System.out.println("Started");
        Socket clientSocket = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        System.out.println("ClientSocket connected");
        while (true) {
            String message = in.readLine();
            System.out.println("message: " + message);
            if (globalState.equals(State.WAIT)) {
                State state = State.valueOf(message);
                System.out.println("state: " + state.name());
                if (state.equals(State.ALL)) {
                    List<JSONObject> list = dao.getAll().stream().map(Product::getJson).collect(Collectors.toList());
                    out.write(new JSONArray(list).toString() + "\n");
                } else {
                    globalState = state;
                    out.write(state.name() + "\n");
                }
            } else {
                runCommand(message);
            }
            out.flush();
        }
    }

    private void runCommand(String message) {
        if (globalState.equals(State.EDIT)) {
            dao.edit(Product.parseJson(new JSONObject(message)));
        } else if (globalState.equals(State.DELETE)) {
            dao.delete(Product.parseJson(new JSONObject(message)));
        } else if (globalState.equals(State.SAVE)) {
            dao.save(Product.parseJson(new JSONObject(message)));
        }
        globalState = State.WAIT;
    }

}


