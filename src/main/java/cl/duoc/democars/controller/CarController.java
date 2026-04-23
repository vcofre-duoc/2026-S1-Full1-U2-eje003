package cl.duoc.democars.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.democars.dto.ApiResponse;
import cl.duoc.democars.dto.carDTO;
import cl.duoc.democars.service.AuthService;
import cl.duoc.democars.service.CarService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cars")
public class CarController {

    private final CarService carService;
    private final AuthService authService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<carDTO>>> getAllCars(
            @RequestParam String username,
            @RequestParam String password) {

        boolean valid = authService.validateLogin(username, password);

        if (!valid) {
            ApiResponse<List<carDTO>> errorResponse =
                    new ApiResponse<>(401, "Credenciales inválidas", null);
            return ResponseEntity.status(401).body(errorResponse);
        }

        List<carDTO> cars = carService.getAllCarsDTO();
        ApiResponse<List<carDTO>> response =
                new ApiResponse<>(200, "Listado de autos", cars);
        return ResponseEntity.ok(response);
    }
}
