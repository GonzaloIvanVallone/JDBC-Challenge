package security.jdbc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import security.jdbc.Exception.CreationFailedException;
import security.jdbc.Exception.FetchFailedException;
import security.jdbc.Model.Admission;
import security.jdbc.Model.Doctor;
import security.jdbc.Service.AdmissionService;
import security.jdbc.Service.DoctorService;

@RestController
@RequestMapping("/api/admissions")
public class AdmissionsController {
    @Autowired
    AdmissionService admissionService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllAdmissions(){
        try{
            return new ResponseEntity<>(admissionService.getAllAdmissions(), HttpStatus.OK);
        }catch(FetchFailedException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getByDoctor")
    public ResponseEntity<?> getAdmissionByDoctor(@RequestParam("id") Long id){
        try{
            return new ResponseEntity<>(admissionService.getAdmissionsByDoctor(id), HttpStatus.OK);
        }catch(FetchFailedException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getByPatient")
    public ResponseEntity<?> getAdmissionByPatient(@RequestParam("id") Long id){
        try{
            return new ResponseEntity<>(admissionService.getAdmissionsByPatient(id), HttpStatus.OK);
        }catch(FetchFailedException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAdmission(@RequestBody Admission admission){
        try{
            admissionService.createAdmission(admission);
            return new ResponseEntity<>("Admission created", HttpStatus.CREATED);
        }catch(CreationFailedException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
