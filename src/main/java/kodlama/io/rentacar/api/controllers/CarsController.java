package kodlama.io.rentacar.api.controllers;

import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.dto.requests.create.CreateCarRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateCarResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllCarsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetCarResponse;
import kodlama.io.rentacar.entities.enums.State;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cars")
public class CarsController {
private final CarService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<GetAllCarsResponse> getAll(@RequestParam(required = false) Boolean choice) {
        return service.getAll(choice);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    GetCarResponse get(@PathVariable int id) {
        return service.getById(id);
    }



    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    CreateCarResponse add(@RequestBody CreateCarRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    CreateCarResponse update(@PathVariable int id, @RequestBody CreateCarRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void delete(@PathVariable int id) {
        service.delete(id);
    }




}
