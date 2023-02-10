package project.kolesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.kolesa.model.CarBody;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CarBodyRepository extends JpaRepository<CarBody,Long> {
}
