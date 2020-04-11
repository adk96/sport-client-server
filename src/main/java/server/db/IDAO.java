package server.db;

import server.model.Product;

import java.util.List;

public interface IDAO {

    void save(Product product);

    void edit(Product product);

    void delete(Product product);

    List<Product> getAll();
}
