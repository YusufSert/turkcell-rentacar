package kodlama.io.rentacar.business.concretes;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.dto.requests.create.CreateCarRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateCarRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateCarResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllCarsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetCarResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateCarResponse;
import kodlama.io.rentacar.entities.Car;
import kodlama.io.rentacar.entities.enums.State;
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
    public List<GetAllCarsResponse> getAll(State state) {
        if(state != null) {

            return repository.getCarByState(state)
               .stream()
               .map(car -> mapper.map(car, GetAllCarsResponse.class))
               .toList();
        }else
            return repository.findAll()
                .stream()
                .map(car -> mapper.map(car, GetAllCarsResponse.class))
                .toList();
    }

    @Override
    public GetCarResponse getById(int id) {
        Car car = repository.findById(id).orElseThrow(() -> new RuntimeException("Araba bulunamadı"));
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
    public UpdateCarResponse update(int id, UpdateCarRequest request) {
        checkIfCarExists(id);
        Car car = mapper.map(request, Car.class);
        car.setId(id);
        repository.save(car);
        return mapper.map(car, UpdateCarResponse.class);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }





    //***********Helper Methods****************

    private void checkIfCarExists(int id) {
        if (!repository.existsById(id)) throw new RuntimeException("Araba bulunamdı!");
    }

    @Override
    public void updateCarState(int id, State state) {
        Car car = repository.findById(id).orElseThrow(() -> new RuntimeException("Araba bulunamadı"));
        car.setState(state);
        repository.save(car);
    }

    public void setCarStateToMaintance(int id) {
        checkIfCarExists(id);
        State state = repository.getCarState(id);
        if(state.equals(State.MAINTANCE)){
            throw new RuntimeException("Car already in maintenance state");
        } else if (state.equals(State.RENTED)) {
            throw new RuntimeException("Car in rented state");
        }
        updateCarState(id, State.MAINTANCE);
    }


}
