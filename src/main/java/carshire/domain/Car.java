package carshire.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Entity
@NoArgsConstructor
public class Car {

    public enum Status {

        Avalible,
        NotAvalible
    };

    @Id
    @GeneratedValue
    private Long id;
    private String manufacturer;
    private String model;
    private Integer yearOfManufacture;
    private Float engineCapacity;
    private Integer vehicleMileage;
    private Float pricePerDay;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    private Integer discount;
    private String registrationNumber;
}
