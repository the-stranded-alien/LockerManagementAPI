package System.LockerManagement.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long mobileNumber;
    private String emailId;
    private Integer pinCode;

    public Customer(String name, Long mobileNumber, String emailId, Integer pinCode) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
        this.pinCode = pinCode;
    }
}
