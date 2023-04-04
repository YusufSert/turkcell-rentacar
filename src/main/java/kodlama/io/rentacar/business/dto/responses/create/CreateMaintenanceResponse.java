package kodlama.io.rentacar.business.dto.responses.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMaintenanceResponse {
    private int id;
    private int carId;
    private Date dateIn;

    private Date dateOut;
    private double cost;

    private boolean isRepaired;

    private String description;
}
