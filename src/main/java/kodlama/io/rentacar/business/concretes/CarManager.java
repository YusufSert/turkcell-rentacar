package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.dto.requests.create.CreateCarRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateCarResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllCarsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetCarResponse;
import kodlama.io.rentacar.entities.Car;
import kodlama.io.rentacar.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class CarManager implements CarService {

    private final CarRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllCarsResponse> getAll() {
        return repository.findAll()
            .stream()
            .map(car -> mapper.map(car, GetAllCarsResponse.class)) // Eşleştir
            .toList(); // Conver stream to lista
    }

    @Override
    public GetCarResponse getById(int id) {
        Car car = repository.findById(id).orElseThrow();
        return mapper.map(car, GetCarResponse.class);
    }

    @Override
    public CreateCarResponse add(CreateCarRequest request) {
        Car car = mapper.map(request, Car.class);
        car.setId(0);
        repository.save(car);
        return mapper.map(car, CreateCarResponse.class);
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
