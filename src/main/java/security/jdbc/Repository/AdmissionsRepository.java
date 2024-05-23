package security.jdbc.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import security.jdbc.Exception.CreationFailedException;
import security.jdbc.Model.Admission;
import security.jdbc.Repository.RowMapper.AdmissionRowMapper;
import javax.sql.DataSource;

import java.sql.*;
import java.util.List;

@Repository
public class AdmissionsRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    DataSource dataSource;
    String GET_ADMISSIONS_BY_DOCTOR = "SELECT a.admission_date, a.discharge_date, a.diagnosis, p.patient_id, p.first_name" +
            " p.last_name, p.dni, d.doctor_id, d.first_name, d.last_name " +
            "FROM ADMISSIONS " +
            "JOIN DOCTOR USING(doctor_id) " +
            "JOIN PATIENT USING(patient_id) " +
            "WHERE doctor_id : ?";
    String GET_ADMISSIONS_BY_PATIENT = "SELECT a.admission_date, a.discharge_date, a.diagnosis, p.patient_id, p.first_name" +
            " p.last_name, p.dni, d.doctor_id, d.first_name, d.last_name " +
            "FROM ADMISSIONS " +
            "JOIN DOCTOR USING(doctor_id) " +
            "JOIN PATIENT USING(patient_id) " +
            "WHERE patient_id : ?";
    String GET_ALL_ADMISSIONS = "SELECT a.admission_date, a.discharge_date, a.diagnosis, p.patient_id, p.first_name" +
            " p.last_name, p.dni, d.doctor_id, d.first_name, d.last_name " +
            "FROM ADMISSIONS a " +
            "JOIN DOCTOR d USING(doctor_id) " +
            "JOIN PATIENT p USING(patient_id) ";
    String CREATE_ADMISSION = "INSERT INTO ADMISSIONS (admission_date, discharge_date, diagnosis) " +
            "VALUES (?,?,?)";

    public List<Admission> getAllAdmissions(){
        return jdbcTemplate.query(GET_ALL_ADMISSIONS, new AdmissionRowMapper());
    }
    public List<Admission> getAdmissionsByDoctor(Long id){
        return jdbcTemplate.query(GET_ADMISSIONS_BY_DOCTOR, new AdmissionRowMapper());
    }
    public List<Admission> getAdmissionsByPatient(Long id){
        return jdbcTemplate.query(GET_ADMISSIONS_BY_PATIENT, new AdmissionRowMapper());
    }
    public void createAdmission(Admission admission) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ADMISSION, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, admission.getDoctorId());
            preparedStatement.setLong(2, admission.getPatientId());
            preparedStatement.setDate(3, Date.valueOf(admission.getAdmissionDate()));
            preparedStatement.setDate(4, Date.valueOf(admission.getDischargeDate()));
            preparedStatement.setString(5, admission.getDiagnosis());
             int rowsAffected = preparedStatement.executeUpdate();
             if (rowsAffected != 1) {
                 throw new CreationFailedException("Error while creating admission");
             }
        }catch (Exception e){
            throw e;
        }
    }
}