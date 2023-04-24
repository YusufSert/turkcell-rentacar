package kodlama.io.rentacar;

import kodlama.io.rentacar.api.controllers.BrandsController;
import kodlama.io.rentacar.api.controllers.CarsController;
import kodlama.io.rentacar.business.dto.requests.create.CreateBrandRequest;
import kodlama.io.rentacar.entities.Car;
import kodlama.io.rentacar.entities.enums.State;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
public class RentACarApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentACarApplication.class, args);


        Iterator<CreateBrandRequest> iterator = new Iterator<CreateBrandRequest>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public CreateBrandRequest next() {
                return null;
            }
        };
    }
}



