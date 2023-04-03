package kodlama.io.rentacar.business.dto.requests.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMaintenanceRequest {
    private int carId;
    private double cost;
    private String description;

    private Date dateIn;

    private Date dateOut;
}
