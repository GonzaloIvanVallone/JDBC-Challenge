package security.jdbc.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import security.jdbc.Exception.CreationFailedException;
import javax.sql.DataSource;
import security.jdbc.Model.Patient;
import security.jdbc.Repository.RowMapper.PatientRowMapper;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PatientRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    DataSource dataSource;
    String GET_PATIENT_DETAIL = "SELECT * FROM PATIENT WHERE ID : ?";
    String GET_ALL_PATIENTS = "SELECT * FROM PATIENT";
    String CREATE_PATIENT = "INSERT INTO PATIENT (id, firstname, lastname, gender, birth_date, city, allergies, weight) VALUES (?,?,?,?,?,?,?)";
    String UPDATE_PATIENT = "UPDATE PATIENT SET firstname=?, lastname=?, gender=?, birth_date=?, city=?, allergies=?, weight=? WHERE id=?";
    public List<Patient> getAllPatients(){
        return jdbcTemplate.query(GET_ALL_PATIENTS, new PatientRowMapper());
    }
    public Patient getPatientDetail(Long id){
        Object[] params = {id};
        return jdbcTemplate.queryForObject(GET_PATIENT_DETAIL, new PatientRowMapper(), params);
    }
    public void createPatient(Patient patient) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PATIENT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, patient.getFirstName());
            preparedStatement.setString(2, patient.getLastName());
            preparedStatement.setString(3, String.valueOf(patient.getGender()));
            preparedStatement.setDate(4, Date.valueOf(patient.getBirthDate()));
            preparedStatement.setString(5, patient.getCity());
            preparedStatement.setString(6, patient.getAllergies());
            preparedStatement.setInt(7, patient.getWeight());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected != 1) {
                throw new CreationFailedException("Error while creating patient");
            }
        }catch (SQLException e){
            throw new CreationFailedException("Error while creating patient");
        }
    }
    public void updatePatient(Patient patient) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PATIENT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, patient.getId());
            preparedStatement.setString(2, patient.getFirstName());
            preparedStatement.setString(3, patient.getLastName());
            preparedStatement.setString(4, String.valueOf(patient.getGender()));
            preparedStatement.setDate(5, Date.valueOf(patient.getBirthDate()));
            preparedStatement.setString(6, patient.getCity());
            preparedStatement.setString(7, patient.getAllergies());
            preparedStatement.setInt(8, patient.getWeight());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected != 1) {
                throw new CreationFailedException("Error while updating patient");
            }
        }catch (SQLException e){
            throw new CreationFailedException("Error while creating patient");
        }
    }
}
