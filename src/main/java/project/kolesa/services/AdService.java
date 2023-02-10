package project.kolesa.services;

import project.kolesa.dto.AdDto;
import project.kolesa.model.*;

import java.util.List;
import java.util.Optional;

public interface AdService {

    List<Ad> getAllAd();

    Ad getAd(Long id);

    Ad addAd(AdDto adDto);

    Ad saveAd(Ad ad);

    Ad updateAd(AdDto adDto, Long id);

    List<Ad> search(Optional<Brand> brandOpt, Optional<City> cityOpt,
                    Optional<Color> colorOpt, Optional<Gearbox> gearboxOpt,
                    Optional<CarBody> carBodyOpt, Optional<EngineType> engineTypeOpt,
                    Optional<Model> modelOpt);

    void deleteAd(Long id);
}
