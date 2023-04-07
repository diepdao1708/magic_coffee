package com.hdv.magiccoffee.features.order;

import java.util.ArrayList;
import java.util.List;

public class OrderUiState {
    // TODO
    List<Product> coffees = new ArrayList<Product>() {{
        add(new Product(1, "https://goo.gl/32YN2B", "Cà phê sữa đá", "39.000đ"));
        add(new Product(2, "https://goo.gl/Wqz4Ev", "Cà phê sữa đá", "39.000đ"));
        add(new Product(3, "https://goo.gl/U7XXdF", "Cà phê sữa đá", "39.000đ"));
        add(new Product(4, "https://goo.gl/ghVPFq", "Cà phê sữa đá", "39.000đ"));
        add(new Product(5, "https://goo.gl/vutGmM", "Cà phê sữa đá", "39.000đ"));
    }};
    List<Product> teas = new ArrayList<Product>() {{
        add(new Product(1, "https://goo.gl/32YN2B", "Trà đá", "39.000đ"));
        add(new Product(2, "https://goo.gl/Wqz4Ev", "Trà đá", "39.000đ"));
        add(new Product(3, "https://goo.gl/U7XXdF", "Trà đá", "39.000đ"));
        add(new Product(4, "https://goo.gl/ghVPFq", "Trà đá", "39.000đ"));
        add(new Product(5, "https://goo.gl/vutGmM", "Trà đá", "39.000đ"));
    }};
    List<Product> fruitJuices = new ArrayList<Product>() {{
        add(new Product(1, "https://goo.gl/32YN2B", "Trà hoa quả", "39.000đ"));
        add(new Product(2, "https://goo.gl/Wqz4Ev", "Trà hoa quả", "39.000đ"));
        add(new Product(3, "https://goo.gl/U7XXdF", "Trà hoa quả", "39.000đ"));
        add(new Product(4, "https://goo.gl/ghVPFq", "Trà hoa quả", "39.000đ"));
        add(new Product(5, "https://goo.gl/vutGmM", "Trà hoa quả", "39.000đ"));
    }};
    List<Product> cakes = new ArrayList<Product>() {{
        add(new Product(1, "https://goo.gl/32YN2B", "Bánh ngọt", "39.000đ"));
        add(new Product(2, "https://goo.gl/Wqz4Ev", "Bánh ngọt", "39.000đ"));
        add(new Product(3, "https://goo.gl/U7XXdF", "Bánh ngọt", "39.000đ"));
        add(new Product(4, "https://goo.gl/ghVPFq", "Bánh ngọt", "39.000đ"));
        add(new Product(5, "https://goo.gl/vutGmM", "Bánh ngọt", "39.000đ"));
    }};
}

class Product {
    int id;
    String image;
    String name;
    String cost;

    public Product(int id, String image, String name, String cost) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getCost() {
        return cost;
    }
}
