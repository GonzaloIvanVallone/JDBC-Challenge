package security.jdbc.Repository.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import security.jdbc.Model.Patient;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientRowMapper implements RowMapper<Patient> {

    @Override
    public Patient mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Patient patient = new Patient();
        patient.setId(resultSet.getLong("patient_id"));
        patient.setFirstName(resultSet.getString("first_name"));
        patient.setLastName(resultSet.getString("last_name"));
        patient.setAllergies(resultSet.getString("allergies"));
        patient.setCity(resultSet.getString("city"));
        patient.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
        patient.setGender(resultSet.getString("gender"));
        patient.setWeight(resultSet.getInt("weight"));
        patient.setDni(resultSet.getInt("dni"));
        return patient;
    }
}