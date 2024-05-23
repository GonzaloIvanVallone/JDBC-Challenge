package security.jdbc.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Admission{
    private Long doctorId;
    private String doctorFirstName;
    private String doctorLastName;
    private Long patientId;
    private String patientFirstName;
    private String patientLastName;
    private Integer dni;
    private LocalDate admissionDate;
    private LocalDate dischargeDate;
    private String diagnosis;

}
