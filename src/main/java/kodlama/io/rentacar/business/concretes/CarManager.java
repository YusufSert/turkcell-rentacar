package kodlama.io.rentacar.business.concretes;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.dto.requests.create.CreateCarRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateCarResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllCarsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetCarResponse;
import kodlama.io.rentacar.entities.Car;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CarManager implements CarService {

    private final CarRepository repository;
    private final ModelMapper mapper;


    @Override
    public List<GetAllCarsResponse> getAll(Boolean choice) {
        if(choice) {
           return repository.findAll().stream()
                .filter(car -> car.getState() != State.MAINTANCE)
                .map(car -> mapper.map(car, GetAllCarsResponse.class)).toList();
        }
            return repository.findAll()
                .stream()
                .map(car -> mapper.map(car, GetAllCarsResponse.class))
                .toList();
    }

    @Override
    public GetCarResponse getById(int id) {
        Car car = repository.findById(id).orElseThrow();
        return mapper.map(car, GetCarResponse.class);
    }

    @Override
    @RequestBody
    public CreateCarResponse add(CreateCarRequest request) {
        Car car = mapper.map(request, Car.class);
        car.setId(0);
        Car dbCar = repository.save(car);
        return mapper.map(dbCar, CreateCarResponse.class);

    }

    @Override
    public CreateCarResponse update(int id, CreateCarRequest request) {
        checkIfCarExists(id);
        Car car = mapper.map(request, Car.class);
        car.setId(id);
        repository.save(car);
        return mapper.map(car, CreateCarResponse.class);
    }


    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }




    private void checkIfCarExists(int id) {
        if (!repository.existsById(id)) throw new RuntimeException("Marka bulunamadı!");
    }
}
