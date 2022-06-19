package System.LockerManagement.controllers;

import System.LockerManagement.models.Customer;
import System.LockerManagement.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
        Customer customer = this.customerService.findCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
        try {
            this.customerService.saveCustomer(customer);
        } catch (Exception e) {
            return new ResponseEntity<>("Unable to Add this Customer !", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Added New Customer Successfully !", HttpStatus.OK);
    }
}

