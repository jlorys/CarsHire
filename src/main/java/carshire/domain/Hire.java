package carshire.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

@Data
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Hire {

    public enum HireStatus {
        Paid,
        NotPaid
    };

    @Id
    @GeneratedValue
    private Long id;
    private Long clientId;
    private Long carId;
    private Long sellerId;
    @Column(columnDefinition = "DATETIME", nullable = true)
    private LocalDateTime hireDate;
    @Column(columnDefinition = "DATETIME", nullable = true)
    private LocalDateTime hireEndDate;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private HireStatus status;
    private BigDecimal priceForHire;
    private BigDecimal defaultInterest;
}
