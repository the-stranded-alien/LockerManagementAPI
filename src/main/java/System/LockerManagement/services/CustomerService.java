package System.LockerManagement.services;

import System.LockerManagement.models.Customer;
import System.LockerManagement.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }

    public Customer findCustomerByMobileNumber(Long mobileNumber) {
        Optional<Customer> optional = this.customerRepository.findByMobileNumber(mobileNumber);
        Customer customer = null;
        if(optional.isPresent()) {
            customer = optional.get();
        } else {
            return null;
        }
        return customer;
    }

    public Customer findCustomerById(Long id) {
        Optional<Customer> optional = this.customerRepository.findById(id);
        Customer customer = null;
        if(optional.isPresent()) {
            customer = optional.get();
        }
        return customer;
    }
}
