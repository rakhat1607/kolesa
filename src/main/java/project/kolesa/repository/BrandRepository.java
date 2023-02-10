package project.kolesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.kolesa.model.Brand;
import project.kolesa.model.Model;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}
