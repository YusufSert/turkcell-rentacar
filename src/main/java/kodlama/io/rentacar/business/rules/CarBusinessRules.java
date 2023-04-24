package kodlama.io.rentacar.business.rules;

import kodlama.io.rentacar.common.constants.Messages;
import kodlama.io.rentacar.core.exceptions.BusinessException;

import kodlama.io.rentacar.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    private final CarRepository repository;

    public void checkIfCarExists(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Car.NotExists);
        }
    }



}
