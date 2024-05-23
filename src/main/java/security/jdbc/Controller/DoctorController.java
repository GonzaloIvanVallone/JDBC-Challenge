package security.jdbc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import security.jdbc.Exception.CreationFailedException;
import security.jdbc.Exception.DeletionException;
import security.jdbc.Exception.FetchFailedException;
import security.jdbc.Model.Doctor;
import security.jdbc.Model.Patient;
import security.jdbc.Service.DoctorService;
import security.jdbc.Service.PatientService;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllDoctors(){
        try{
            return new ResponseEntity<>(doctorService.getAllDoctors(), HttpStatus.OK);
        }catch(FetchFailedException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getDetail/{id}")
    public ResponseEntity<?> getDoctor(@RequestParam("id") Long id ){
        try{
            return new ResponseEntity<>(doctorService.getDetails(id), HttpStatus.OK);
        }catch(FetchFailedException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<?> createDoctor(@RequestBody Doctor doctor){
        try{
            doctorService.createDoctor(doctor);
            return new ResponseEntity<>("Doctor created", HttpStatus.CREATED);
        }catch(CreationFailedException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateDoctor(@RequestBody Doctor doctor){
        try{
            doctorService.updateDoctor(doctor);
            return new ResponseEntity<>("Doctor updated", HttpStatus.OK);
        }catch(CreationFailedException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDoctor(@RequestParam("id") Long id){
        try{
            doctorService.deleteDoctor(id);
            return new ResponseEntity<>("Doctor updated", HttpStatus.OK);
        }catch(DeletionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
