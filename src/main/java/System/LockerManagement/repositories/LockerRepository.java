package System.LockerManagement.repositories;

import System.LockerManagement.models.Locker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LockerRepository extends JpaRepository<Locker, Long> {
    Optional<Locker> findTopByLocationPinCodeAndStatus(Integer pinCode, Locker.Status status);
}
