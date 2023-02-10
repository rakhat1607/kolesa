package project.kolesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.kolesa.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
