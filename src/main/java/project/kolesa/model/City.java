package project.kolesa.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "city")
@Getter
@Setter
public class City extends BaseEntity{
    @Column(name = "city")
    private String city;
}
