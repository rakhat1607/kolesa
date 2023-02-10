package project.kolesa.services;

import project.kolesa.model.City;

import java.util.List;
import java.util.Optional;

public interface CityService {
    City  getCity(Long id);
    Optional<City>findCity(Long id);
    List<City> getAllCity();
}
