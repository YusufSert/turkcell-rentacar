package kodlama.io.rentacar.business.dto.requests.create;

import kodlama.io.rentacar.business.dto.requests.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalRequest {
    private int carId;
    private double dailyPrice;
    private int rentedForDays;
    private LocalDateTime startDate;
    private PaymentRequest paymentRequest;
}
