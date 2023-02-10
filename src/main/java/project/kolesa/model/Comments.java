package project.kolesa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_deleted is false")
public class Comments extends BaseEntity {
    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Ad ad;

    @Column(name = "is_deleted", columnDefinition="boolean default false")
    private boolean deleted = false;
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

//    @Column(name = "post_date")
//    private LocalDateTime postDate;
//
//    @PrePersist
//    public void prePersist(){
//        this.postDate = LocalDateTime.now();
//    }


}
