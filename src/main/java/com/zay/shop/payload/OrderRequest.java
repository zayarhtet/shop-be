package com.zay.shop.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


public class OrderRequest {
    @Getter @Setter
    private Long orderNo;

    @Getter @Setter
    private String status;

    @Getter @Setter
    private Integer customerId;

    @Getter @Setter
    private ArrayList<Integer> productId;

    @Getter @Setter
    private Integer quantity;
}
