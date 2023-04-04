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
        checkIfMaintenanceExist(id);
        Maintenance maintenance = repository.findById(id).orElseThrow();
        return mapper.map(maintenance, GetMaintenanceResponse.class);
    }

    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {
        // Map Request to Maintance
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(0);
        maintenance.setRepaired(false);

        // Eğer id of the car not found in car table "save()" will throw exception(FK constraint)
        repository.save(maintenance);

        // Get the id of the car that will send to maintance
        int carId = maintenance.getCar().getId();

        // If car already in maintance or in rent method will throw exception
        carService.setCarStateToMaintance(carId);

        // Map Maintance to Response object
        return mapper.map(maintenance, CreateMaintenanceResponse.class);
    }

    @Override
    public UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request) {
        checkIfMaintenanceExist(id);
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(id);

        // Save the maintance and map Maintance to Response
        UpdateMaintenanceResponse response = mapper.map(repository.save(maintenance), UpdateMaintenanceResponse.class);

        //Check if is_repaired column not false, if not change state of the car
        if(maintenance.isRepaired()) {

            // Get the id of the car that repaired
            int carId = maintenance.getCar().getId();

            //Update the state of the car
            carService.updateCarState(carId, State.AVAILABLE);
        }
        return response;
    }

    @Override
    public void delete(int id) {
       checkIfMaintenanceExist(id);
        repository.deleteById(id);
    }

    //***********Helper Methods****************

    private void checkIfMaintenanceExist(int id) {
        if (!repository.existsById(id)) throw new RuntimeException("No maintenance record found !");
    }

}
