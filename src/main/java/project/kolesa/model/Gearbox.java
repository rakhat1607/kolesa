package project.kolesa.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "gearbox")
@Getter
@Setter
public class Gearbox extends BaseEntity{
    @Column(name = "gearbox")
    private String gearbox;
}
