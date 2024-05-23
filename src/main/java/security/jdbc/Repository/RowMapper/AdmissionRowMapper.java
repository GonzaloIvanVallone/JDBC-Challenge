package security.jdbc.Repository.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import security.jdbc.Model.Admission;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdmissionRowMapper implements RowMapper<Admission> {

    @Override
    public Admission mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Admission admission = new Admission();
            admission.setDoctorId(resultSet.getLong("doctor_id"));
            admission.setDoctorFirstName(resultSet.getString("first_name"));
            admission.setDoctorLastName(resultSet.getString("last_name"));
            admission.setPatientId(resultSet.getLong("patient_id"));
            admission.setPatientFirstName(resultSet.getString("first_name"));
            admission.setPatientLastName(resultSet.getString("last_name"));
            admission.setDni(resultSet.getInt("dni"));
            admission.setAdmissionDate(resultSet.getDate("admission_date").toLocalDate());
            admission.setDischargeDate(resultSet.getDate("discharge_date").toLocalDate());
            admission.setDiagnosis(resultSet.getString("diagnosis"));
            return admission;
    }
}