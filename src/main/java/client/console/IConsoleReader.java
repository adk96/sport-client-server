package client.console;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;

public interface IConsoleReader {

    JSONObject edit(BufferedReader reader) throws IOException;

    JSONObject save(BufferedReader reader) throws IOException;

    JSONObject delete(BufferedReader reader) throws IOException;

}
