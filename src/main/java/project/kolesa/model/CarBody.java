package project.kolesa.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "car_body")
@Getter
@Setter
public class CarBody extends BaseEntity{
    @Column(name = "car_body")
    private String carBody;
}
