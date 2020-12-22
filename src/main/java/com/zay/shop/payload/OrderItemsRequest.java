package com.zay.shop.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class OrderItemsRequest {
    @Getter @Setter
    private Integer quantity;

    @Getter @Setter
    private Integer orderId;

    @Getter @Setter
    private Integer productId;
}
