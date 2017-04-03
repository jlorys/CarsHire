package carshire.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class Car {

    private Long id;
    private String manufacturer;
    private String name;
}
