package security.jdbc.Repository.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import security.jdbc.Model.Doctor;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorRowMapper implements RowMapper<Doctor> {
    @Override
    public Doctor mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Doctor doctor = new Doctor();
        doctor.setId(resultSet.getLong("doctor_id"));
        doctor.setFirstName(resultSet.getString("first_name"));
        doctor.setLastName(resultSet.getString("last_name"));
        doctor.setSpecialty(resultSet.getString("specialty"));
        doctor.setActive(resultSet.getBoolean("active"));
        return doctor;
    }
}