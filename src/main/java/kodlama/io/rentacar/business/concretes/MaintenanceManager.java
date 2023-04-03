package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.abstracts.MaintenanceService;
import kodlama.io.rentacar.business.dto.requests.create.CreateCarRequest;
import kodlama.io.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateCarRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllMaintenancesResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetCarResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateCarResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateMaintenanceResponse;
import kodlama.io.rentacar.entities.Brand;
import kodlama.io.rentacar.entities.Car;
import kodlama.io.rentacar.entities.Maintenance;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class MaintenanceManager implements MaintenanceService {
    private final MaintenanceRepository repository;
    private final CarService carService;
    private final ModelMapper mapper;

    @Override
    public List<GetAllMaintenancesResponse> getAll() {
        List<Maintenance> responseList = repository.findAll();
        return responseList.stream().map(m -> mapper.map(m, GetAllMaintenancesResponse.class)).toList();
    }

    @Override
    public GetMaintenanceResponse getById(int id) {
        Maintenance maintenance = repository.findById(id).orElseThrow();
        return mapper.map(maintenance, GetMaintenanceResponse.class);
    }

    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {
        // Map Request to Maintance
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(0);

        // Eğer id of the car not found in car table "save()" will throw error
        repository.save(maintenance);

        // Get the id of the car that will send to maintance
        int carId = maintenance.getCar().getId();

        // If car already in maintance method will throw exception
        carService.updateCarState(carId, State.MAINTANCE);

        // Map Maintance to Response object
        return mapper.map(maintenance, CreateMaintenanceResponse.class);
    }

    @Override
    public UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request) {
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(id);

        // Save the maintance and map Maintance to Response
        UpdateMaintenanceResponse response = mapper.map(repository.save(maintenance), UpdateMaintenanceResponse.class);

        //Check if date_out column not nul, if not change state of the car
        if(maintenance.getDateOut() != null) {

            // Get the id of the car that will send to maintance
            int carId = maintenance.getCar().getId();

            //Update the state of the car  carService.updateCarState(id, State.AVAILABLE);
            carService.updateCarState(carId, State.AVAILABLE);
        }
        return response;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

}
