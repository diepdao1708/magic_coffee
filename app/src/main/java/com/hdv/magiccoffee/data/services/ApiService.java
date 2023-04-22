package com.hdv.magiccoffee.data.services;

import com.hdv.magiccoffee.data.retrofit.ApiClient;

public class ApiService {
    private static final AuthService authService = ApiClient.retrofit().create(AuthService.class);
    private static final UserService userService = ApiClient.retrofit().create(UserService.class);
    private static final ProductService productService = ApiClient.retrofit().create(ProductService.class);

    public static AuthService getAuthService() {
        return authService;
    }

    public static UserService getUserService() {
        return userService;
    }

    public static ProductService getProductService() {
        return productService;
    }
}
