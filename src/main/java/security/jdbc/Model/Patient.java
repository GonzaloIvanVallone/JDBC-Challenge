package security.jdbc.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Patient {
    private Long id;
    private String firstName;
    private String lastName;
    private char gender;
    private LocalDate birthDate;
    private String city;
    private String allergies;
    private Integer weight;
    private Integer dni;
}
