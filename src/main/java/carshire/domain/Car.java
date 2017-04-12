package carshire.domain;

import javax.persistence.Entity;
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

    	@Id @GeneratedValue
	private Long id;
        private String manufacturer;
	private String name;
}
