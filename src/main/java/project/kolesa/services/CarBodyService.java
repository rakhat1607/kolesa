package project.kolesa.services;

import project.kolesa.model.CarBody;

import java.util.List;
import java.util.Optional;

public interface CarBodyService {
    CarBody getCarBody(Long id);
    Optional<CarBody>findCarBody(Long id);
    List<CarBody> getAllCarBody();
}
