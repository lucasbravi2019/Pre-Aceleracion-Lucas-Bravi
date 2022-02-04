package com.alkemy.alkemy.character.entity;

import com.alkemy.alkemy.movie.entity.Movie;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "characters")
@SQLDelete(sql = "UPDATE characters SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private String image;
    private Byte age;
    private Float weight;
    private String history;

    private Boolean deleted = Boolean.FALSE;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "movie_character",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> movies;

}
