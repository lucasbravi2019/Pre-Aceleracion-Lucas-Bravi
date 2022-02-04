package com.alkemy.alkemy.gender.entity;

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
@SQLDelete(sql = "UPDATE gender SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private String image;
    private Boolean deleted = Boolean.FALSE;

    @OneToMany(mappedBy = "gender")
    private List<Movie> movies;

}
