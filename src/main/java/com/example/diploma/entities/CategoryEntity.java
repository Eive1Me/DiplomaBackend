package com.example.diploma.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category_")
@Setter
@Getter
@Builder(toBuilder = true)
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity userId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "desc_")
    private String desc;

    @Column(name = "colour")
    private String colour;
}
