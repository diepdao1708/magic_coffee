package com.hdv.magiccoffee.features.home;

import java.util.ArrayList;
import java.util.List;

public class HomeUiState {
    // TODO
    List<Header> images = new ArrayList<Header>() {{
        add(new Header(1, "https://goo.gl/32YN2B"));
        add(new Header(2, "https://goo.gl/Wqz4Ev"));
        add(new Header(3, "https://goo.gl/U7XXdF"));
        add(new Header(4, "https://goo.gl/ghVPFq"));
        add(new Header(5, "https://goo.gl/vutGmM"));
    }};
    List<Suggestion> suggestions = new ArrayList<Suggestion>() {{
        add(new Suggestion(1, "https://goo.gl/32YN2B", "Cà phê sữa đá", "39.000đ"));
        add(new Suggestion(2, "https://goo.gl/Wqz4Ev", "Cà phê sữa đá", "39.000đ"));
        add(new Suggestion(3, "https://goo.gl/U7XXdF", "Cà phê sữa đá", "39.000đ"));
        add(new Suggestion(4, "https://goo.gl/ghVPFq", "Cà phê sữa đá", "39.000đ"));
        add(new Suggestion(5, "https://goo.gl/vutGmM", "Cà phê sữa đá", "39.000đ"));
    }};

    List<Voucher> vouchers = new ArrayList<Voucher>() {{
        add(new Voucher(1, "https://goo.gl/32YN2B", "FREE SHIP", "1 voucher", "Hết hạn 30/04/2023"));
        add(new Voucher(2, "https://goo.gl/Wqz4Ev", "FREE SHIP", "1 voucher", "Hết hạn 30/04/2023"));
        add(new Voucher(3, "https://goo.gl/U7XXdF", "FREE SHIP", "1 voucher", "Hết hạn 30/04/2023"));
        add(new Voucher(4, "https://goo.gl/ghVPFq", "FREE SHIP", "1 voucher", "Hết hạn 30/04/2023"));
        add(new Voucher(5, "https://goo.gl/vutGmM", "FREE SHIP", "1 voucher", "Hết hạn 30/04/2023"));
    }};
}

class Header {
    int id;
    String image;

    public Header(int id, String image) {
        this.id = id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }
}

class Suggestion {
    int id;
    String image;
    String name;
    String cost;

    public Suggestion(int id, String image, String name, String cost) {
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

class Voucher {
    int id;
    String image;
    String name;
    String quantity;
    String date;

    public Voucher(int id, String image, String name, String quantity, String date) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.quantity = quantity;
        this.date = date;
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

    public String getQuantity() {
        return quantity;
    }

    public String getDate() {
        return date;
    }
}
