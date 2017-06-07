package carshire.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Kuba
 */
@Data
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    /**
     *
     */
    public enum CarStatus {

        /**
         *
         */
        Avalible,

        /**
         *
         */
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
    private BigDecimal pricePerDay;
    private BigDecimal pricePerDayAfterDiscount;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CarStatus status;
    private Integer discount;
    private String registrationNumber;
}
