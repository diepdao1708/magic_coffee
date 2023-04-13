package com.hdv.magiccoffee.features.checkout;

import com.hdv.magiccoffee.features.commondata.Product;
import com.hdv.magiccoffee.features.commondata.Size;

import java.util.ArrayList;
import java.util.List;

public class CheckoutUiState {

    //TODO
    List<Product> products = new ArrayList<Product>() {{
        add(new Product(1, "https://goo.gl/32YN2B", "Cà phê sữa đá", 39f, "Nếu chuộng vị cà phê đậm đà, bùng nổ và thích vị đường đen ngọt thơm, Đường Đen Sữa Đá đích thị là thức đồ uống dành cho bạn.", 1, Size.LARGE));
        add(new Product(2, "https://goo.gl/Wqz4Ev", "Cà phê sữa đá", 39f, "Nếu chuộng vị cà phê đậm đà, bùng nổ và thích vị đường đen ngọt thơm, Đường Đen Sữa Đá đích thị là thức đồ uống dành cho bạn.", 3, Size.SMALL));
        add(new Product(5, "https://goo.gl/vutGmM", "Cà phê sữa đá", 39f, "Nếu chuộng vị cà phê đậm đà, bùng nổ và thích vị đường đen ngọt thơm, Đường Đen Sữa Đá đích thị là thức đồ uống dành cho bạn.", 2, Size.MEDIUM));
    }};
}
