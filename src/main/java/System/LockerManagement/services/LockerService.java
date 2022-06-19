package System.LockerManagement.services;

import System.LockerManagement.models.Customer;
import System.LockerManagement.models.Locker;
import System.LockerManagement.models.LockerRequest;
import System.LockerManagement.models.LockerResponse;
import System.LockerManagement.repositories.LockerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
public class LockerService {

    @Autowired
    private LockerRepository lockerRepository;

    @Autowired
    private CustomerService customerService;

    public Locker findLockerByPinCodeAndStatus(Integer pinCode, Locker.Status status) {
        Optional<Locker> optional = this.lockerRepository.findTopByLocationPinCodeAndStatus(pinCode, status);
        Locker locker = null;
        if(optional.isPresent()) {
            locker = optional.get();
        }
        return locker;
    }

    public Locker findLockerById(Long id) {
        Optional<Locker> optional = this.lockerRepository.findById(id);
        Locker locker = null;
        if(optional.isPresent()) {
            locker = optional.get();
        }
        return locker;
    }

    public Locker save(Locker locker) {
        return this.lockerRepository.save(locker);
    }

    public String addNew(Integer pin) {
        if(pin < 100000 || pin > 999999) return "Invalid Pin Entered !";
        Locker locker = new Locker(pin);
        this.save(locker);
        return "Locker Added !";
    }

    public String book(LockerRequest lockerRequest) {
        Customer customer = this.customerService.findCustomerByMobileNumber(lockerRequest.getMobileNumber());
        if(customer == null) return "This Customer Doesn't Exist !";
        Locker availableLocker = this.findLockerByPinCodeAndStatus(lockerRequest.getPinCode(), Locker.Status.AVAILABLE);
        if(availableLocker == null) return "No Locker Available at Given Location";
        Locker bookedLocker = availableLocker.book(lockerRequest.getMobileNumber());
        this.save(bookedLocker);
        return "Success";
    }

    public boolean sendOTP(Locker locker) {
        if(locker.getStatus() == Locker.Status.BOOKED) {
            Random rand = new Random();
            Integer otp = rand.nextInt((9999 - 100) + 1) + 10;
            locker.sendOTP(otp);
            this.lockerRepository.save(locker);
            return true;
        } else return false;
    }

    public boolean open(Locker locker, Integer otp) {
        if(!Objects.equals(locker.getOtp(), otp)) return false;
        else {
            locker.open();
            this.lockerRepository.save(locker);
            return true;
        }
    }

    public LockerResponse status(Long id) {
        Locker locker = this.findLockerById(id);
        return new LockerResponse(locker);
    }
}

