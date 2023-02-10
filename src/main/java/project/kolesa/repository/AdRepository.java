package project.kolesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.kolesa.model.*;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
    List<Ad> findAllByAuthor(User user);

    @Query("select ad from Ad ad where (ad.carBody = :carBody or :carBody is null) and (ad.color  = :color or :color is null) " +
            " and (ad.city =:city or :city is null ) and (ad.gearbox =:gearbox or :gearbox is null ) " +
            " and (ad.engineType =:engineType or :engineType is null ) and (ad.model =:model or :model is null )" +
            " and (ad.model.brand = :brand or :brand is null )")
    List<Ad> search(@Param("carBody") CarBody carBody, @Param("color") Color color, @Param("city")City city,
                    @Param("gearbox")Gearbox gearbox, @Param("engineType")EngineType engineType,@Param("model")Model model,
                    @Param("brand") Brand brand);
}
