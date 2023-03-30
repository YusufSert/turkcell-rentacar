package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.ModelService;
import kodlama.io.rentacar.business.dto.requests.create.CreateModelRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateModelResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllModelsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetModelResponse;
import kodlama.io.rentacar.entities.Model;
import kodlama.io.rentacar.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private final ModelRepository repository;
    private final ModelMapper mapper;


    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> modelList = repository.findAll();
        List<GetAllModelsResponse> responses = new ArrayList<>();

        for(Model model : modelList) {
            GetAllModelsResponse response =  mapper.map(model, GetAllModelsResponse.class);
            responses.add(response);
        }
        return responses;
    }

    @Override
    public GetModelResponse getById(int id) {
        Model model = repository.findById(id).orElseThrow();
        return mapper.map(model, GetModelResponse.class);
    }

    @Override
    public CreateModelResponse add(CreateModelRequest request) {
        Model model = mapper.map(request, Model.class);
        repository.save(model);
        return mapper.map(model, CreateModelResponse.class);
    }

    @Override
    public CreateModelResponse update(int id, CreateModelRequest request) {
        Model model = repository.findById(id).orElseThrow();
        model.setName(request.getName());
        repository.save(model);
        return mapper.map(model, CreateModelResponse.class);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
