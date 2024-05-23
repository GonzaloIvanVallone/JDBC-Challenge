package security.jdbc.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import security.jdbc.Exception.CreationFailedException;
import security.jdbc.Exception.FetchFailedException;
import security.jdbc.Model.Patient;
import security.jdbc.Repository.PatientRepository;

import java.sql.SQLException;
import java.util.List;
@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;
    public List<Patient> getAllPatients(){
        try{
            return patientRepository.getAllPatients();
        }catch(Exception e){
            throw new FetchFailedException("Error while fetching patients", e);
        }
    }
    public Patient getDetails(Long id){
        try {
            return patientRepository.getPatientDetail(id);
        }catch(Exception e){
            throw new FetchFailedException("Error while fetching patient details", e);
        }
    }
    public void createPatient(Patient patient){
        try {
            patientRepository.createPatient(patient);
        }catch(Exception e){
            throw new CreationFailedException("Error while creating patient", e);
        }
    }
    public void updatePatient(Patient patient){
        try {
            patientRepository.updatePatient(patient);
        }catch(Exception e){
            throw new CreationFailedException("Error while updating patient", e);
        }
    }
}
