package com.eclesiastelucien.com.lucienstore.modules.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eclesiastelucien.com.lucienstore.commons.dtos.ApiResponse;
import com.eclesiastelucien.com.lucienstore.modules.cart.dtos.requests.CartRequest;
import com.eclesiastelucien.com.lucienstore.modules.cart.dtos.requests.UpdateCartRequest;
import com.eclesiastelucien.com.lucienstore.modules.cart.models.CartCost;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "carts", description = "CRUD REST APIs - Add item to cart, Update item in cart, Get items from Cart, Delete item from cart")
@RestController
@RequestMapping("api/v1/carts/items")
public class CartController {
    @Autowired
    private CartServiceImpl cartServiceImpl;

    @PostMapping
    public ResponseEntity<ApiResponse> addToCart(@RequestBody CartRequest carRequest) {

        cartServiceImpl.addToCart(carRequest);

        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CartCost> getCartItems() {
        CartCost cartCost = cartServiceImpl.listCartItems();

        return new ResponseEntity<CartCost>(cartCost, HttpStatus.OK);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<ApiResponse> updateCartItem(@PathVariable("itemId") Long productId,
            @RequestBody UpdateCartRequest updateCartRequest) {
        cartServiceImpl.updateCartItem(updateCartRequest.getQuantity(), productId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("itemId") Long productId) {
        cartServiceImpl.deleteCartItem(productId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
    }
}
