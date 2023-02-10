package project.kolesa.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "engine_type")
@Getter
@Setter
public class EngineType extends BaseEntity{
    @Column(name = "engine_type")
    private String engineType;
}
