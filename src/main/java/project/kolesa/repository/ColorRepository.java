package project.kolesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.kolesa.model.Color;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ColorRepository extends JpaRepository<Color,Long> {
}
