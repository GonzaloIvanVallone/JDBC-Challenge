package security.jdbc.Repository;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import security.jdbc.Exception.CreationFailedException;
import security.jdbc.Exception.DeletionException;
import security.jdbc.Model.Doctor;
import security.jdbc.Repository.RowMapper.DoctorRowMapper;

import java.sql.*;
import java.util.List;

@Repository
public class DoctorRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    DataSource dataSource;
    String GET_DOCTOR_DETAIL = "SELECT * FROM DOCTOR WHERE ID : ?";
    String GET_ALL_DOCTORS = "SELECT * FROM DOCTOR";
    String CREATE_DOCTOR = "INSERT INTO DOCTOR (id, firstname, lastname, specialty, active) VALUES (null,?,?,?,?)";
    String UPDATE_DOCTOR = "UPDATE DOCTOR SET () WHERE";
    String DELETE_DOCTOR = "DELETE FROM DOCTOR WHERE ID = ?";
    public List<Doctor> getAllDoctors(){
        return jdbcTemplate.query(GET_ALL_DOCTORS, new DoctorRowMapper());
    }
    public Doctor getDoctorDetail(Long id){
        return jdbcTemplate.queryForObject(GET_DOCTOR_DETAIL, new DoctorRowMapper());
    }
    public void createDoctor(Doctor doctor) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_DOCTOR, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, doctor.getId());
            preparedStatement.setString(2, doctor.getFirstName());
            preparedStatement.setString(3, doctor.getLastName());
            preparedStatement.setString(4, doctor.getSpecialty());
            preparedStatement.setBoolean(5, doctor.getActive());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected != 1) {
                throw new CreationFailedException("Error while creating doctor");
            }
        }catch (Exception e){
            throw e;
        }
    }
    public void updateDoctor(Doctor doctor) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DOCTOR, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, doctor.getId());
            preparedStatement.setString(2, doctor.getFirstName());
            preparedStatement.setString(3, doctor.getLastName());
            preparedStatement.setString(4, doctor.getSpecialty());
            preparedStatement.setBoolean(5, doctor.getActive());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected != 1) {
                throw new CreationFailedException("Error while updating doctor");
            }
        }catch (Exception e){
            throw e;
        }
    }
    public void deleteDoctor(Long id) throws SQLException{
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DOCTOR)) {
            getDoctorDetail(id);
            preparedStatement.setLong(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted < 0) {
                throw new DeletionException("No Doctor found with ID " + id + " to delete.", e);
            }
        }
    }
}