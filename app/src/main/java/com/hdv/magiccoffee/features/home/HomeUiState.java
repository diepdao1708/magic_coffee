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
        add(new Product(1, "https://goo.gl/32YN2B", "Cà phê sữa đá", 39f, "Nếu chuộng vị cà phê đậm đà, bùng nổ và thích vị đường đen ngọt thơm, Đường Đen Sữa Đá đích thị là thức đồ uống dành cho bạn."));
        add(new Product(2, "https://goo.gl/Wqz4Ev", "Cà phê sữa đá", 39f, "Nếu chuộng vị cà phê đậm đà, bùng nổ và thích vị đường đen ngọt thơm, Đường Đen Sữa Đá đích thị là thức đồ uống dành cho bạn."));
        add(new Product(3, "https://goo.gl/U7XXdF", "Cà phê sữa đá", 39f, "Nếu chuộng vị cà phê đậm đà, bùng nổ và thích vị đường đen ngọt thơm, Đường Đen Sữa Đá đích thị là thức đồ uống dành cho bạn."));
        add(new Product(4, "https://goo.gl/ghVPFq", "Cà phê sữa đá", 39f, "Nếu chuộng vị cà phê đậm đà, bùng nổ và thích vị đường đen ngọt thơm, Đường Đen Sữa Đá đích thị là thức đồ uống dành cho bạn."));
        add(new Product(5, "https://goo.gl/vutGmM", "Cà phê sữa đá", 39f, "Nếu chuộng vị cà phê đậm đà, bùng nổ và thích vị đường đen ngọt thơm, Đường Đen Sữa Đá đích thị là thức đồ uống dành cho bạn."));
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

