package security.jdbc.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import security.jdbc.Exception.CreationFailedException;
import javax.sql.DataSource;

import security.jdbc.Exception.FetchFailedException;
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
    String GET_PATIENT_DETAIL = "SELECT * FROM patients WHERE patient_id=?";
    String GET_ALL_PATIENTS = "SELECT * FROM patients";
    String CREATE_PATIENT = "INSERT INTO patients (first_name, last_name, gender, birth_date, city, allergies, weight, dni) VALUES (?,?,?,?,?,?,?,?)";
    String UPDATE_PATIENT = "UPDATE patients SET first_name=?, last_name=?, gender=?, birth_date=?, city=?, allergies=?, weight=?, dni=? WHERE patient_id=?";
    public List<Patient> getAllPatients(){
        return jdbcTemplate.query(GET_ALL_PATIENTS, new PatientRowMapper());
    }
    public Patient getPatientDetail(Long id){
        try {
            Object[] params = {id};
            return jdbcTemplate.queryForObject(GET_PATIENT_DETAIL, new PatientRowMapper(), params);
        }catch(Exception e){
            throw new FetchFailedException("Error while searching for patient details", e);
        }
    }
    public void createPatient(Patient patient){
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PATIENT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, patient.getFirstName());
            preparedStatement.setString(2, patient.getLastName());
            preparedStatement.setString(3, patient.getGender());
            preparedStatement.setDate(4, Date.valueOf(patient.getBirthDate()));
            preparedStatement.setString(5, patient.getCity());
            preparedStatement.setString(6, patient.getAllergies());
            preparedStatement.setInt(7, patient.getWeight());
            preparedStatement.setInt(8, patient.getDni());

            int rowsAffected = preparedStatement.executeUpdate();//possible schema problems (data type, table name)
            if(rowsAffected != 1){
                throw new CreationFailedException("Error while creating patient: no rows affected");
            }
        }catch(SQLException e){
            throw new CreationFailedException("Error while creating patient");
        }
    }
    public void updatePatient(Patient patient){
        try(Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PATIENT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, patient.getFirstName());
            preparedStatement.setString(2, patient.getLastName());
            preparedStatement.setString(3, patient.getGender());
            preparedStatement.setDate(4, Date.valueOf(patient.getBirthDate()));
            preparedStatement.setString(5, patient.getCity());
            preparedStatement.setString(6, patient.getAllergies());
            preparedStatement.setInt(7, patient.getWeight());
            preparedStatement.setInt(8, patient.getDni());
            preparedStatement.setLong(9, patient.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected != 1){
                throw new CreationFailedException("Error while updating patient");
            }
        }catch(SQLException e){
            throw new CreationFailedException("Error while creating patient");
        }
    }
}
