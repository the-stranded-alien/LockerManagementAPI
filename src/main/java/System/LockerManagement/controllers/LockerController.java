package System.LockerManagement.controllers;

import System.LockerManagement.models.Locker;
import System.LockerManagement.models.LockerRequest;
import System.LockerManagement.models.LockerResponse;
import System.LockerManagement.services.CustomerService;
import System.LockerManagement.services.LockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/lockers")
public class LockerController {
    @Autowired
    private LockerService lockerService;

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> add(@RequestBody LockerRequest lockerRequest) {
        String response = this.lockerService.addNew(lockerRequest.getPinCode());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<LockerResponse> status(@PathVariable("id") Long id) {
        LockerResponse response = this.lockerService.status(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<String> book(@RequestBody LockerRequest lockerRequest) {
        String bookingStatus = this.lockerService.book(lockerRequest);
        if(Objects.equals(bookingStatus, "Success")) {
            return new ResponseEntity<>("Locker Booked Successfully !", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(bookingStatus, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}/notify")
    public ResponseEntity<String> sendOTP(@PathVariable(value = "id") Long id) {
        Locker locker = this.lockerService.findLockerById(id);
        if(locker == null) {
            return new ResponseEntity<>("Locker Not Found !", HttpStatus.BAD_REQUEST);
        }
        boolean isOtpSent = this.lockerService.sendOTP(locker);
        if(isOtpSent) {
            return new ResponseEntity<>("OTP Send Successfully, OTP = " + locker.getOtp().toString(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Can't Send OTP For This Locker !", HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/{id}/open")
    public ResponseEntity<String> open(@RequestBody Locker locker, @PathVariable("id") Long id) {
        Locker existingLocker = this.lockerService.findLockerById(id);
        if(existingLocker == null) {
            return new ResponseEntity<>("Locker Not Found !", HttpStatus.BAD_REQUEST);
        }
        if(existingLocker.getStatus() != Locker.Status.OTP_SENT) {
            return new ResponseEntity<>("Locker Not Accessible !", HttpStatus.BAD_REQUEST);
        }
        boolean isLockerOpened = this.lockerService.open(existingLocker, locker.getOtp());
        if(isLockerOpened) {
            return new ResponseEntity<>("Locker Opened !", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid OTP !", HttpStatus.BAD_REQUEST);
    }
}

