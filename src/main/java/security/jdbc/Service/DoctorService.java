package security.jdbc.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import security.jdbc.Exception.CreationFailedException;
import security.jdbc.Exception.DeletionException;
import security.jdbc.Exception.FetchFailedException;
import security.jdbc.Model.Admission;
import security.jdbc.Model.Doctor;
import security.jdbc.Repository.DoctorRepository;

import java.sql.SQLException;
import java.util.List;
@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    public List<Doctor> getAllDoctors(){
        try{
            return doctorRepository.getAllDoctors();
        }catch(Exception e){
            throw new FetchFailedException("Error while searching for doctors", e);
        }
    }
    public Doctor getDetails(Long id){
        try {
            return doctorRepository.getDoctorDetail(id);
        }catch(Exception e){
            throw new FetchFailedException("Error while searching for doctor details", e);
        }
    }
    public void createDoctor(Doctor doctor){
        try {
            doctorRepository.createDoctor(doctor);
        }catch(Exception e){
            throw new CreationFailedException("Error while creating a doctor", e);
        }
    }
    public void updateDoctor(Doctor doctor){
        try {
            doctorRepository.updateDoctor(doctor);
        }catch(Exception e){
            throw new CreationFailedException("Error while updating a doctor", e);
        }
    }
    public void deleteDoctor(Long id){
        try {
            doctorRepository.deleteDoctor(id);
        }catch(Exception e){
            throw new DeletionException("Error while deleting a doctor", e);
        }
    }
}
