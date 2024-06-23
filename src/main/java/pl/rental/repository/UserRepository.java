package pl.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rental.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
