package com.hdv.magiccoffee.features.home;

import com.hdv.magiccoffee.features.commondata.Product;
import com.hdv.magiccoffee.features.commondata.Voucher;

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
    List<Product> products = new ArrayList<Product>() {{
        add(new Product(1, "PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAxOC43MyAxOC43NCI+PGRlZnM+PHN0eWxlPi5jbHMtMXtmaWxsOiNkMWQzZDQ7fTwvc3R5bGU+PC9kZWZzPjx0aXRsZT4xX01CPC90aXRsZT48ZyBpZD0iTGF5ZXJfMiIgZGF0YS1uYW1lPSJMYXllciAyIj48ZyBpZD0iTGF5ZXJfMS0yIiBkYXRhLW5hbWU9IkxheWVyIDEiPjxwYXRoIGNsYXNzPSJjbHMtMSIgZD0iTTcuMjYsMTAuNDZWNS43M2MwLS42LjMtLjg1LjY3LS41NEw5LjQ5LDYuNDZsMS4zNCwxLjA5LDEuNTYsMS4yN2EuNjguNjgsMCwwLDEsMCwxLjA5bC0xLjU2LDEuMjdMOS40OSwxMi4yOCw3LjkzLDEzLjU1Yy0uMzcuMy0uNjcuMDUtLjY3LS41NVoiLz48cGF0aCBjbGFzcz0iY2xzLTEiIGQ9Ik05LjM3LDE4Ljc0YTkuMzcsOS4zNywwLDEsMSw5LjM3LTkuMzdBOS4zOCw5LjM4LDAsMCwxLDkuMzcsMTguNzRaTTkuMzcuNmE4Ljc3LDguNzcsMCwxLDAsOC43Nyw4Ljc3QTguNzgsOC43OCwwLDAsMCw5LjM3LjZaIi8+PC9nPjwvZz48L3N2Zz4=", "Cà phê sữa đá", 39f, "Nếu chuộng vị cà phê đậm đà, bùng nổ và thích vị đường đen ngọt thơm, Đường Đen Sữa Đá đích thị là thức đồ uống dành cho bạn."));
    }};

    List<Voucher> vouchers = new ArrayList<Voucher>() {{
        add(new Voucher(1, "https://goo.gl/32YN2B", "FREE SHIP", "1 voucher", "Hết hạn 30/04/2023", "Giảm 50% đơn từ 4 ly nước trở nên kèm FREESHIP"));
        add(new Voucher(2, "https://goo.gl/Wqz4Ev", "FREE SHIP", "1 voucher", "Hết hạn 30/04/2023", "Giảm 50% đơn từ 4 ly nước trở nên kèm FREESHIP"));
        add(new Voucher(3, "https://goo.gl/U7XXdF", "FREE SHIP", "1 voucher", "Hết hạn 30/04/2023", "Giảm 50% đơn từ 4 ly nước trở nên kèm FREESHIP"));
        add(new Voucher(4, "https://goo.gl/ghVPFq", "FREE SHIP", "1 voucher", "Hết hạn 30/04/2023", "Giảm 50% đơn từ 4 ly nước trở nên kèm FREESHIP"));
        add(new Voucher(5, "https://goo.gl/vutGmM", "FREE SHIP", "1 voucher", "Hết hạn 30/04/2023", "Giảm 50% đơn từ 4 ly nước trở nên kèm FREESHIP"));
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

