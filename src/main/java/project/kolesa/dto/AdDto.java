package project.kolesa.dto;

import lombok.Getter;
import lombok.Setter;
import project.kolesa.model.*;

import java.util.Set;

@Getter
@Setter
public class AdDto {
    private Long id;
    private City city;
    private Brand brand;
    private Model model;
    private int yearOfIssue;
    private double price;
    private EngineType engineType;
    private double mileage;
    private double engineVolume;
    private Color color;
    private CarBody carBody;
    private Gearbox gearbox;
    private UserDto author;
    private Set<Ad> favorites;
}
