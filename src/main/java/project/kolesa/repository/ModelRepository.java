package project.kolesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.kolesa.model.Brand;
import project.kolesa.model.Model;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ModelRepository extends JpaRepository<Model,Long> {
    List<Model> findAllByBrand(Brand brand);
}
