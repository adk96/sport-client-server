package client.console;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class ConsoleReader implements IConsoleReader {

    @Override
    public JSONObject edit(BufferedReader reader) throws IOException {
        JSONObject jsonObject = new JSONObject();
        readID(reader, jsonObject);
        System.out.println("Enter product title:");
        String title = reader.readLine();
        jsonObject.put("title", title);
        System.out.println("Enter product description:");
        String description = reader.readLine();
        jsonObject.put("description", description);
        readQuantity(reader, jsonObject);
        readPrice(reader, jsonObject);
        return jsonObject;
    }

    @Override
    public JSONObject save(BufferedReader reader) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "0");
        System.out.println("Enter product title:");
        String title = reader.readLine();
        jsonObject.put("title", title);
        System.out.println("Enter product description:");
        String description = reader.readLine();
        jsonObject.put("description", description);
        readQuantity(reader, jsonObject);
        readPrice(reader, jsonObject);
        return jsonObject;
    }

    @Override
    public JSONObject delete(BufferedReader reader) throws IOException {
        JSONObject jsonObject = new JSONObject();
        readID(reader, jsonObject);
        jsonObject.put("title", "0");
        jsonObject.put("description", "0");
        jsonObject.put("quantity", "0");
        jsonObject.put("price", "0");
        return jsonObject;
    }

    private void readQuantity(BufferedReader reader, JSONObject jsonObject) throws IOException {
        System.out.println("Enter product quantity:");
        String quantity = reader.readLine();
        if (Pattern.matches("([0-9]*)", quantity)) {
            jsonObject.put("quantity", quantity);
        } else {
            System.out.println("quantity value must be Integer");
            readQuantity(reader, jsonObject);
        }
    }

    private void readID(BufferedReader reader, JSONObject jsonObject) throws IOException {
        System.out.println("Enter product ID:");
        String id = reader.readLine();
        if (Pattern.matches("([0-9]*)", id)) {
            jsonObject.put("id", id);
        } else {
            System.out.println("ID value must be Integer");
            readID(reader, jsonObject);
        }
    }

    private void readPrice(BufferedReader reader, JSONObject jsonObject) throws IOException {
        System.out.println("Enter product price:");
        String price = reader.readLine();
        if (Pattern.matches("([0-9]*)\\.([0-9]*)", price)) {
            jsonObject.put("price", price);
        } else {
            System.out.println("price value must be Double");
            readPrice(reader, jsonObject);
        }
    }
}
