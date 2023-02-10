package project.kolesa.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "color")
@Getter
@Setter
public class Color extends BaseEntity{
    @Column(name = "color")
    private String color;
}
