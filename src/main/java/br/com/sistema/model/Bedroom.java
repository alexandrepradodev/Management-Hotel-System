package br.com.sistema.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quartos")
public class Bedroom {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bedroom_seq")
    @GenericGenerator(
            name = "bedroom_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "bedroom_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "100"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "numero")
    private Long bedroomNumber;

    @Column(name = "capacidade_total")
    private int capacity;

    @Column(name = "valor_diaria")
    private BigDecimal dailyRate;

    @ManyToOne
    @JoinColumn(name = "tipo_id")
    private Tier bedroomTier;

    @OneToMany(mappedBy = "bedroom")
    private List<Reservation> reservations = new ArrayList<>();


    public Bedroom() {
    }

    public Bedroom(int capacity, BigDecimal dailyRate, Tier bedroomTier) {
        this.capacity = capacity;
        this.dailyRate = dailyRate;
        this.bedroomTier = bedroomTier;
    }

    public Long getBedroomNumber() {
        return bedroomNumber;
    }

    public void setBedroomNumber(Long bedroomNumber) {
        this.bedroomNumber = bedroomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;
    }

    public Tier getBedroomTier() {
        return bedroomTier;
    }

    public void setBedroomTier(Tier bedroomTier) {
        this.bedroomTier = bedroomTier;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
