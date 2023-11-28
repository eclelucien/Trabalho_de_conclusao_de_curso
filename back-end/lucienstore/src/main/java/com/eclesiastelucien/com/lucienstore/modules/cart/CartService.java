package com.eclesiastelucien.com.lucienstore.modules.cart;

import org.springframework.stereotype.Service;

import com.eclesiastelucien.com.lucienstore.modules.cart.dtos.requests.CartRequest;
import com.eclesiastelucien.com.lucienstore.modules.cart.models.CartCost;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface CartService {

    public void addToCart(CartRequest addToCartDto);

    public CartCost listCartItems();

    public void updateCartItem(int quantity, Long itemId);

    public void deleteCartItem(Long product);
}
