package com.ecommerce.shopping_api.service;
import com.ecommerce.shopping_client.dto.ProductDTO;
import com.ecommerce.shopping_client.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    private String productApiURL = "http://localhost:8081";
//    public ProductDTO getProductByIdentifier(String productIdentifier) {
//
//        try {
//            WebClient webClient = WebClient.builder()
//                    .baseUrl(productApiURL)
//                    .build();
//
//            Mono<ProductDTO> product = webClient.get()
//                    .uri("/product/" + productIdentifier)
//                    .retrieve()
//                    .bodyToMono(ProductDTO.class);
//
//            return product.block();
//        } catch (Exception e) {
//            throw new RuntimeException("Product not found");
//        }
//
//    }

public ProductDTO getProductByIdentifier(String productIdentifier) {
    try {
        return WebClient.builder().baseUrl(productApiURL).build()
                .get().uri("/product/{id}", productIdentifier)
                .retrieve()
                .bodyToMono(ProductDTO.class)
                .block();
    } catch (WebClientResponseException.NotFound ex) {
        throw new ProductNotFoundException();
    }
}

}

