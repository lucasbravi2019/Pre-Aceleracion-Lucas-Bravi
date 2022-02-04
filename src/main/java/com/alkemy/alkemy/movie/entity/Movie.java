package com.alkemy.alkemy.movie.entity;

import com.alkemy.alkemy.character.entity.Character;
import com.alkemy.alkemy.gender.entity.Gender;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE movie SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;
    private String image;

    @Temporal(TemporalType.DATE)
    private Date createdAt = new Date();
    private Boolean deleted = Boolean.FALSE;

    @ManyToMany(mappedBy = "movies", cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    private List<Character> characters;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private Gender gender;


}
