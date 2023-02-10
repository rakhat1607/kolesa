package project.kolesa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "t_image")
@Getter
@Setter
@NoArgsConstructor
public class Image extends BaseEntity{
    @Column(name = "image")
    private String image;

    public Image(String image,Ad ad) {
        this.image =image;
        this.ad = ad;
    }

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Ad ad;

}
