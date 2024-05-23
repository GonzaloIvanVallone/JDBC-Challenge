package security.jdbc.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import security.jdbc.Exception.CreationFailedException;
import security.jdbc.Exception.FetchFailedException;
import security.jdbc.Model.Admission;
import security.jdbc.Repository.AdmissionsRepository;
import java.sql.SQLException;
import java.util.List;


@Service
public class AdmissionService {
    @Autowired
    AdmissionsRepository admissionsRepository;
    public List<Admission> getAllAdmissions(){
        try{
            return admissionsRepository.getAllAdmissions();
        }catch(Exception e){
            throw new FetchFailedException("Error while searching for admissions", e);
        }
    }
    public List<Admission> getAdmissionsByDoctor(Long id){
        try{
            return admissionsRepository.getAdmissionsByDoctor(id);
        }catch(Exception e){
            throw new FetchFailedException("Error while searching for admissions by doctor", e);
        }
    }
    public List<Admission> getAdmissionsByPatient(Long id){
        try {
            return admissionsRepository.getAdmissionsByPatient(id);
        }catch(Exception e){
            throw new FetchFailedException("Error while searching for admissions by patient", e);
        }
    }
    public void createAdmission(Admission admission){
        try {
            admissionsRepository.createAdmission(admission);
        }catch(Exception e){
            throw new CreationFailedException("Error while creating admission", e);
        }
    }
}
