package security.jdbc.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Doctor {
    private Long id;
    private String firstName;
    private String lastName;
    private String specialty;
    private Boolean active;
}
