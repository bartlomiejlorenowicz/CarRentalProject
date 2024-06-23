package pl.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rental.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
