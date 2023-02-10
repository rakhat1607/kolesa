package project.kolesa.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "brand")
@Getter
@Setter
public class Brand extends BaseEntity{
    @Column(name = "brand")
    private String brand;

}
