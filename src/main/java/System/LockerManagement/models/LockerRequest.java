package System.LockerManagement.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LockerRequest {
    private Long mobileNumber;
    private Integer pinCode;
}
