package System.LockerManagement.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LockerResponse {
    private Integer pinCode;
    private Locker.Status status;
    private Integer otp;

    public LockerResponse(Locker locker) {
        this.pinCode = locker.getLocationPinCode();
        this.status = locker.getStatus();
        this.otp = locker.getOtp();
    }
}
