package br.com.sistema.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tipo_dos_quartos")
public class Tier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToMany(mappedBy = "hospedes", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "nome")
    private List<Bedroom> bedrooms = new ArrayList<>();
    private String tierName;


}
