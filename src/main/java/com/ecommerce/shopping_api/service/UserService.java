package com.ecommerce.shopping_api.service;

import com.ecommerce.shopping_client.dto.UserDTO;
import com.ecommerce.shopping_client.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    private String userApiURL = "http://localhost:8080";

//    public UserDTO getUserByCpf(String cpf) {
//        try {
//            WebClient webClient = WebClient.builder()
//                    .baseUrl(userApiURL)
//                    .build();
//
//            Mono<UserDTO> user = webClient.get()
//                    .uri("/user/" + cpf + "/cpf")
//                    .retrieve()
//                    .bodyToMono(UserDTO.class);
//
//            return user.block();
//        } catch (Exception e) {
//            throw new RuntimeException("User not found");
//        }
//
//    }
    public UserDTO getUserByCpf(String cpf) {
        try {
            return WebClient.builder().baseUrl(userApiURL).build()
                    .get().uri("/user/{cpf}/cpf", cpf)
                    .retrieve()
                    .bodyToMono(UserDTO.class)
                    .block();
        } catch (WebClientResponseException.NotFound ex) {
            throw new UserNotFoundException();
        }
    }

}