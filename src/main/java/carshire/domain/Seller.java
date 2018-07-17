package carshire.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Grzesiek
 */
@Data
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"login"})})
public class Seller {

    public enum Rights {
        Admin,
        Manager,
        Employee
    };

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    @Column(name = "login")
    private String login;
    private String eMail;
    private String password;
    private String city;
    private String street;
    private String houseNumber;
    @Column(name = "rights")
    @Enumerated(EnumType.STRING)
    private Rights rights;
}
