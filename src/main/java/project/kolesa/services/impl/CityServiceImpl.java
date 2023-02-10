package project.kolesa.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.kolesa.model.City;
import project.kolesa.repository.CityRepository;
import project.kolesa.services.CityService;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;

    @Override
    public Optional<City> findCity(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public City getCity(Long id) {
        return cityRepository.findById(id).orElseThrow();
    }

    @Override
    public List<City> getAllCity() {
        return cityRepository.findAll();
    }
}
