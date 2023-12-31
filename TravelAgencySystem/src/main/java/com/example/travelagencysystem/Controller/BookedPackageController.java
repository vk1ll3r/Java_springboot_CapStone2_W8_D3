package com.example.travelagencysystem.Controller;

import com.example.travelagencysystem.Model.BookedPackage;
import com.example.travelagencysystem.Model.Package;
import com.example.travelagencysystem.Model.Travelers;
import com.example.travelagencysystem.Service.BookedPackageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/booked")
@RequiredArgsConstructor
public class BookedPackageController {


    private final BookedPackageService service;



    @GetMapping("/get")
    public ResponseEntity getPackages(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getBookedPackages());
    }

    @PostMapping("/add/{pack_id}/{user_id}")
    public ResponseEntity addPackage(@PathVariable Integer pack_id,@PathVariable Integer user_id, @Valid @RequestBody BookedPackage bookedPackage , Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        service.addBookedPackage(pack_id,user_id,bookedPackage);
        return ResponseEntity.status(HttpStatus.OK).body("Booked Package Added");
    }

    @PutMapping("/update/{pack_id}/{user_id}/{bookedId}")
    public ResponseEntity updatePackage(@PathVariable Integer user_id, @PathVariable Integer pack_id, @PathVariable Integer bookedId,@Valid @RequestBody BookedPackage p,Errors errors){
        if (errors.hasErrors()){
            String message =errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        service.updatePackage(pack_id,user_id,bookedId,p);
        return ResponseEntity.status(HttpStatus.OK).body("Booked Package Updated");
    }

    @DeleteMapping("/delete/{pack_id}/{user_id}")
    public ResponseEntity deletePackage(@PathVariable Integer pack_id, @PathVariable Integer user_id){
        service.deletePackage(pack_id,user_id);
        return ResponseEntity.status(HttpStatus.OK).body("Booked Package deleted");
    }

    @GetMapping("/trips/{user_id}")
    public ResponseEntity travelHistory(@PathVariable Integer user_id){
        return ResponseEntity.status(HttpStatus.OK).body(service.completedTrips(user_id));
    }



}