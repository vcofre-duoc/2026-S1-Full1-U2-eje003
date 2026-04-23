package cl.duoc.democars.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import cl.duoc.democars.dto.LoginRequest;
import cl.duoc.democars.dto.LoginResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final WebClient webClient;

    public boolean validateLogin(String username, String password) {
        LoginRequest request = new LoginRequest();
        request.setUsername(username);
        request.setPassword(password);

        try {
            LoginResponse response = webClient.post()
                    .uri("/login")
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(LoginResponse.class)
                    .block();

                    return response != null && response.getCode() == 200;
        } catch (Exception e) {
            return false;
        }
    }
}