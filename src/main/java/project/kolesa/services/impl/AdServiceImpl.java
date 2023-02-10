package project.kolesa.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.kolesa.dto.AdDto;
import project.kolesa.model.*;
import project.kolesa.repository.AdRepository;
import project.kolesa.services.AdService;
import project.kolesa.services.UserService;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdServiceImpl implements AdService {
    @Autowired
    private AdRepository adRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private EntityManager em;
    @Override
    public List<Ad> getAllAd() {
        return adRepository.findAll();
    }

    @Override
    public Ad getAd(Long id) {
        return adRepository.findById(id).orElseThrow();
    }

    @Override
    public Ad addAd(AdDto adDto) {
        Ad ad = new Ad();
        ad.setId(adDto.getId());
        ad.setCity(adDto.getCity());
        ad.setModel(adDto.getModel());
        ad.setYearOfIssue(adDto.getYearOfIssue());
        ad.setPrice(adDto.getPrice());
        ad.setEngineType(adDto.getEngineType());
        ad.setMileage(adDto.getMileage());
        ad.setEngineVolume(adDto.getEngineVolume());
        ad.setColor(adDto.getColor());
        ad.setCarBody(adDto.getCarBody());
        ad.setGearbox(adDto.getGearbox());
        ad.setAuthor(userService.getCurrentUser());
        return adRepository.save(ad);
    }

    @Override
    public Ad saveAd(Ad ad) {
        return adRepository.save(ad);
    }

    @Override
    public Ad updateAd(AdDto adDto, Long id) {
        Ad ad = adRepository.findById(id).orElseThrow();
        ad.setId(adDto.getId());
        ad.setCity(adDto.getCity());
        ad.setModel(adDto.getModel());
        ad.setYearOfIssue(adDto.getYearOfIssue());
        ad.setPrice(adDto.getPrice());
        ad.setEngineType(adDto.getEngineType());
        ad.setMileage(adDto.getMileage());
        ad.setEngineVolume(adDto.getEngineVolume());
        ad.setColor(adDto.getColor());
        ad.setCarBody(adDto.getCarBody());
        ad.setGearbox(adDto.getGearbox());
        ad.setAuthor(userService.getCurrentUser());
        return adRepository.save(ad);
    }

    @Override
    public List<Ad> search(Optional<Brand> brandOpt, Optional<City> cityOpt,
                           Optional<Color> colorOpt, Optional<Gearbox> gearboxOpt,
                           Optional<CarBody> carBodyOpt, Optional<EngineType> engineTypeOpt,
                           Optional<Model> modelOpt) {
        return  adRepository.search(
                carBodyOpt.orElse(null),
                colorOpt.orElse(null),
                cityOpt.orElse(null),
                gearboxOpt.orElse(null),
                engineTypeOpt.orElse(null),
                modelOpt.orElse(null),
                brandOpt.orElse(null)
        );
    }

    @Override
    public void deleteAd(Long id) {
        var adOpt = adRepository.findById(id);
        if(adOpt.isPresent()) {
            var ad = adOpt.get();
            ad.setDeleted(true);
            adRepository.save(ad);
        }
    }
}
