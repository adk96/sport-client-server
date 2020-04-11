package server.model;

import org.json.JSONObject;

import java.io.Serializable;

public class Product implements Serializable {
    private Integer id;
    private String title;
    private String description;
    private int quantity;
    private double price;

    public Product(String title, String description, int quantity, double price) {
        this.title = title;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(Integer id, String title, String description, int quantity, double price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public JSONObject getJson() {
        JSONObject object = new JSONObject();
        object.put("id", getId());
        object.put("title", getTitle());
        object.put("description", getDescription());
        object.put("quantity", getQuantity());
        object.put("price", getPrice());
        return object;
    }

    public static Product parseJson(JSONObject object) {
        Integer id = object.getInt("id");
        String title = object.getString("title");
        String description = object.getString("description");
        int quantity = object.getInt("quantity");
        double price = object.getDouble("price");
        return new Product(id, title, description, quantity, price);
    }

}
