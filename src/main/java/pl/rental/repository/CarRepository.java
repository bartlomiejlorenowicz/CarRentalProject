package pl.rental.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.rental.model.Car;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Page<Car> findAll(Pageable pageable);


    @Query("SELECT c FROM Car c WHERE " +
            "(:make IS NULL OR LOWER(c.make) = LOWER(:make)) AND " +
            "(:model IS NULL OR LOWER(c.model) = LOWER(:model)) AND " +
            "(:year IS NULL OR c.year = :year) AND " +
            "(:price IS NULL OR c.price <= :price) AND " +
            "(:type IS NULL OR LOWER(c.type) LIKE LOWER(CONCAT('%', :type, '%')))")
    List<Car> findCarsByCriteria(
            @Param("make") String make,
            @Param("model") String model,
            @Param("year") Integer year,
            @Param("price") BigDecimal price,
            @Param("type") String type);

}
