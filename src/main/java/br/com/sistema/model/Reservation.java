package br.com.sistema.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "reservas")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "numero_quarto")
    private Bedroom bedroom;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Guest guest;

    @Column(name = "criancas")
    private int children;

    @Column(name = "adultos")
    private int adults;

    @Column(name = "check_in")
    private LocalDate checkIn;

    @Column(name = "check_out")
    private LocalDate checkOut;

    @Column(name = "valor_da_estadia")
    private BigDecimal stayValue;

    public Reservation() {
    }

    public Reservation(Bedroom bedroom, Guest guest, int children,
                       int adults, LocalDate checkIn, LocalDate checkOut, BigDecimal stayValue) {

        this.bedroom = bedroom;
        this.guest = guest;
        this.children = children;
        this.adults = adults;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.stayValue = stayValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bedroom getBedroom() {
        return bedroom;
    }

    public void setBedroom(Bedroom bedroom) {
        this.bedroom = bedroom;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public BigDecimal getStayValue() {
        return stayValue;
    }

    public void setStayValue(BigDecimal stayValue) {
        this.stayValue = stayValue;
    }
}
