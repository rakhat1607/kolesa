package project.kolesa.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_ad")
@Getter
@Setter
@Where(clause = "is_deleted is false")
public class Ad extends BaseEntity{

    @ManyToOne(fetch = FetchType.EAGER)
    private City city;

    @ManyToOne(fetch = FetchType.EAGER)
    private Model model;

    @Column(name = "year_of_issue")
    private int yearOfIssue;

    @Column(name = "price")
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    private EngineType engineType;

    @Column(name = "mileage")
    private double mileage;

    @Column(name = "engine_volume")
    private double engineVolume;

    @ManyToOne(fetch = FetchType.EAGER)
    private Color color;

    @Column(name = "photo")
    private String photo;
    @ManyToOne(fetch = FetchType.EAGER)
    private CarBody carBody;

    @ManyToOne(fetch = FetchType.EAGER)
    private Gearbox gearbox;

    @Column(name = "is_deleted", columnDefinition="boolean default false")
    private boolean deleted = false;
    @Column(name = "post_date")
    private LocalDateTime postDate;

    @PrePersist
    public void prePersist(){
        this.postDate = LocalDateTime.now();
    }

    @ManyToOne
    private User author;

    @OneToMany(mappedBy = "ad")
    private List<Image> image;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ad ad = (Ad) o;
        return getId() != null && Objects.equals(getId(), ad.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
