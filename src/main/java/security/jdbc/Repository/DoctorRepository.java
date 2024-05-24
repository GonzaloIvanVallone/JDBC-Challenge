package security.jdbc.Repository;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import security.jdbc.Exception.CreationFailedException;
import security.jdbc.Exception.DeletionException;
import security.jdbc.Exception.FetchFailedException;
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
    String GET_DOCTOR_DETAIL = "SELECT * FROM doctors WHERE doctor_id=?";
    String GET_ALL_DOCTORS = "SELECT * FROM doctors";
    String CREATE_DOCTOR = "INSERT INTO doctors (first_name, last_name, specialty, active) VALUES (?,?,?,true)";
    String UPDATE_DOCTOR = "UPDATE doctors SET first_name=?, last_name=?, specialty=?, active=? WHERE doctor_id=?";
    String DELETE_DOCTOR = "UPDATE doctors SET active=false WHERE doctor_id=?";//"DELETE FROM doctors WHERE doctor_id=?";
    public List<Doctor> getAllDoctors(){
        return jdbcTemplate.query(GET_ALL_DOCTORS, new DoctorRowMapper());
    }
    public Doctor getDoctorDetail(Long id){
        try {
            Object[] params = {id};
            return jdbcTemplate.queryForObject(GET_DOCTOR_DETAIL, new DoctorRowMapper(), params);
        }catch(Exception e){
            throw new FetchFailedException("Error while searching for doctor details", e);
        }
    }
    public void createDoctor(Doctor doctor){
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_DOCTOR, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, doctor.getFirstName());
            preparedStatement.setString(2, doctor.getLastName());
            preparedStatement.setString(3, doctor.getSpecialty());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected != 1) {
                throw new CreationFailedException("Error while creating doctor: no rows affected");
            }
        }catch(SQLException e){
            throw new CreationFailedException("Error while creating doctor");
        }
    }
    public void updateDoctor(Doctor doctor){
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DOCTOR, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, doctor.getFirstName());
            preparedStatement.setString(2, doctor.getLastName());
            preparedStatement.setString(3, doctor.getSpecialty());
            preparedStatement.setBoolean(4, doctor.getActive());
            preparedStatement.setLong(5,doctor.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected != 1) {
                throw new CreationFailedException("Error while updating doctor: no rows affected");
            }
        }catch(SQLException e){
            throw new CreationFailedException("Error while creating patient");
        }
    }
    public void deleteDoctor(Long id){
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DOCTOR)) {
            preparedStatement.setLong(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted < 0) {
                throw new DeletionException("No Doctor found with ID " + id + " to delete.");
            }
        }catch(SQLException e){
            throw new DeletionException("Error while deleting doctor");
        }
    }
}