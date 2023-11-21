package br.com.sistema.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "hospedes")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String name;
    @Column(name = "data_nascimento")
    private LocalDate birthday;
    @Column(name = "idade")
    private int age;

    public Guest() {
    }

    public Guest(String name, LocalDate birthday, int age) {
        this.name = name;
        this.birthday = birthday;
        this.age = age;
    }
}
