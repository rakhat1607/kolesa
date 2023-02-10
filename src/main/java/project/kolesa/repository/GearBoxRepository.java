package project.kolesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.kolesa.model.Gearbox;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface GearBoxRepository extends JpaRepository<Gearbox,Long> {
}
