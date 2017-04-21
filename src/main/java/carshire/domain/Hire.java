package carshire.domain;

import java.time.LocalDateTime;
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
public class Hire {

    public enum Status {
        Paid,
        NotPaid
    };

    @Id
    @GeneratedValue
    private Long id;
    private Long clientId;
    private Long carId;
    private Long sellerId;
    private LocalDateTime hireDate;
    private LocalDateTime hireEndDate;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    private Float priceForHire;
    private Float defaultInterest;
}
