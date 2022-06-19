package System.LockerManagement.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Locker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public enum Status {
        AVAILABLE,
        UNAVAILABLE,
        BOOKED,
        OTP_SENT;
    };
    private Integer locationPinCode;
    private Integer otp;
    private Long customerMobileNumber;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Locker(Integer pin) {
        this.status = Status.AVAILABLE;
        this.locationPinCode = pin;
        this.otp = null;
        this.customerMobileNumber = null;
    }

    public Locker book(Long phoneNumber) {
        this.customerMobileNumber = phoneNumber;
        this.status = Status.BOOKED;
        return this;
    }

    public Locker sendOTP(Integer otp) {
        this.status = Status.OTP_SENT;
        this.otp = otp;
        return this;
    }

    public void open() {
        this.status = Status.AVAILABLE;
        this.customerMobileNumber = null;
        this.otp = null;
    }
}