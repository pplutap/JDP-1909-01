package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/carts")
public class CartController {

    private List<ProductDto> sampleProducts = new ArrayList<>();
    private List<CartDto> sampleCarts = new ArrayList<>();

    public CartController() {
        initializeSampleProducts();
        List<ProductDto> cartProducts = new ArrayList<>();
        cartProducts.add(sampleProducts.get(0));
        cartProducts.add(sampleProducts.get(1));
        cartProducts.add(sampleProducts.get(7));
        sampleCarts.add(new CartDto(1L, cartProducts));
    }

    private void initializeSampleProducts() {
        String[] names = {"kurtka zimowa", "płaszcz", "buty", "rękawiczki", "sandały", "zegarek", "złote kolczyki",
                "krawat", "szelki", "pasek", "koszulka", "bezrękawnik", "apaszka"};
        String descripiton = "Pellentesque tempus interdum quam ut rhoncus. Donec ullamcorper turpis dolor. Donec " +
                "euismod pretium eros et eleifend. Aliquam vulputate faucibus lorem non auctor. Vivamus erat turpis, " +
                "molestie a nisl non, scelerisque luctus enim. Nunc mi mi, laoreet ac mollis nec, pharetra sit amet " +
                "tortor. Vivamus a bibendum purus.";
        int[] prices = {100, 150, 100, 50, 80, 250, 300, 50, 40, 100, 50, 30, 20};
        long[] groupIds = {1, 1, 4, 2, 4, 3, 3, 2, 2, 2, 1, 1, 2};
        for (int id = 0; id < names.length; id++) {
            sampleProducts.add(new ProductDto(
                    (long) (id + 1),
                    names[id],
                    descripiton,
                    new BigDecimal(prices[id]),
                    groupIds[id]
            ));
        }
    }

    @GetMapping("{id}/products")
    public List<ProductDto> getProductsFromCart(@PathVariable Long id) {
        CartDto cart = sampleCarts.stream()
                .filter(cartDto -> cartDto.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cart not found."));
        return cart.getProducts();
    }

    @PostMapping
    public CartDto createCart() {
        long maxId = sampleCarts.stream()
                .map(CartDto::getId)
                .max(Long::compareTo)
                .orElse(0L);
        CartDto newCart = new CartDto(maxId + 1, new ArrayList<>());
        sampleCarts.add(newCart);
        return newCart;
    }

    @PutMapping("/{id}/products/{productId}")
    public List<ProductDto> addProductToCart(@PathVariable Long id, @PathVariable Long productId) {
        CartDto cart = sampleCarts.stream()
                .filter(cartDto -> cartDto.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cart not found."));
        ProductDto product = sampleProducts.stream()
                .filter(productDto -> productDto.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found."));
        cart.getProducts().add(product);
        return cart.getProducts();
    }

    @DeleteMapping("/{id}/products/{productId}")
    public List<ProductDto> removeProductFromCart(@PathVariable Long id, @PathVariable Long productId) {
        CartDto cart = sampleCarts.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cart not found."));
        Optional<ProductDto> product = cart.getProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst();
        product.ifPresent(p -> cart.getProducts().remove(p));
        return cart.getProducts();
    }

    @PostMapping("/{id}/orders")
    public OrderDto createOrder(@PathVariable Long id, @RequestBody OrderDto orderDto) {
        CartDto cart = sampleCarts.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cart not found."));
        OrderDto newOrder = new OrderDto(
                100L,
                orderDto.getBuyerId(),
                orderDto.getSellerId(),
                LocalDateTime.now(),
                new ArrayList<>(cart.getProducts()),
                "AWAITING PAYMENT",
                orderDto.getDeliveryDate()
        );
        sampleCarts.remove(cart);
        return newOrder;
    }
    
}
