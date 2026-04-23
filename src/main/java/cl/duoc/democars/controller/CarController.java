package cl.duoc.democars.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.democars.dto.ApiResponse;
import cl.duoc.democars.dto.carDTO;
import cl.duoc.democars.service.CarService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cars")
public class CarController {

    private final CarService carService;


    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<carDTO>>> getAllCars() {
        List<carDTO> cars = carService.getAllCarsDTO();
        ApiResponse<List<carDTO>> response =
                new ApiResponse<>(200, "Listado de autos", cars);
        return ResponseEntity.ok(response);
    }
}
