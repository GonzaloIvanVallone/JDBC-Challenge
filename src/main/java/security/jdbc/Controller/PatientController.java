package security.jdbc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import security.jdbc.Exception.FetchFailedException;
import security.jdbc.Model.Patient;
import security.jdbc.Service.PatientService;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    PatientService patientService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllPatients(){
        try{
           return new ResponseEntity<>(patientService.getAllPatients(), HttpStatus.OK);
        } catch (FetchFailedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getDetail/{id}")
    public ResponseEntity<?> getPatient(@PathVariable("id") Long id ){
        try{
            return new ResponseEntity<>(patientService.getDetails(id), HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<?> createPatient(@RequestBody Patient patient){
        try{
            patientService.createPatient(patient);
            return new ResponseEntity<>("Patient created", HttpStatus.CREATED);
        }catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<?> updatePatient(@RequestBody Patient patient){
        try{
            patientService.updatePatient(patient);
            return new ResponseEntity<>("Patient updated", HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
