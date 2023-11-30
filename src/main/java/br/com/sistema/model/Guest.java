package br.com.sistema.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hospedes")
public class Guest {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String name;
    private String cpf;
    private String email;
    @Column(name = "data_nascimento")
    private LocalDate birthday;
    @Column(name = "idade")
    private int age;
    @OneToMany(mappedBy = "guest", cascade = CascadeType.REMOVE)
    private List<Reservation> reservations = new ArrayList<>();

    public Guest() {
    }

    public Guest(String name, String cpf, String email, LocalDate birthday, int age) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.birthday = birthday;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StringBuilder stringBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nId: ").append(id);
        stringBuilder.append("\nNome: ").append(name);
        stringBuilder.append("\nCPF: ").append(cpf);
        stringBuilder.append("\nEmail: ").append(email);
        stringBuilder.append("\nData de nascimento: ").append(birthday.format(dateTimeFormatter));
        stringBuilder.append("\nIdade: ").append(age).append(" anos");
        return stringBuilder;

    }
}
