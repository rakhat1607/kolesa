package project.kolesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.kolesa.model.EngineType;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface EngineTypeRepository extends JpaRepository<EngineType,Long> {
}
