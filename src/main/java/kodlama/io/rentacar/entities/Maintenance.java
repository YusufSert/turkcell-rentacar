package kodlama.io.rentacar.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.modelmapper.internal.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "maintenances")
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Car car;


    @Column(nullable = false)
    private Date dateIn;

    @Column(nullable = true)
    private Date dateOut;

    private double cost;


    private boolean isRepaired;

    private String description;
}
