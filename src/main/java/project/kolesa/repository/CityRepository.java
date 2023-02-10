package project.kolesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.kolesa.model.City;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CityRepository extends JpaRepository<City,Long> {
}
