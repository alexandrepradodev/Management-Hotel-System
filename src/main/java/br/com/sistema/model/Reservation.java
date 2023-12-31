package br.com.sistema.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "reservas")
public class Reservation {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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

    public BigDecimal calculateStay(BigDecimal valuePerDay, Integer days, Integer adults, Integer children) {
        return valuePerDay.multiply(BigDecimal.valueOf(days))
                .add(BigDecimal.valueOf(adults).multiply(BigDecimal.valueOf(75.0)).multiply(BigDecimal.valueOf(days)))
                .add(BigDecimal.valueOf(children).multiply(BigDecimal.valueOf(50.0).multiply(BigDecimal.valueOf(days))));
    }

    public int calculateDays(LocalDate checkIn, LocalDate checkOut) {
        Period period = Period.between(checkIn, checkOut);
        return period.getDays();
    }

    public StringBuilder stringBuilder2() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nId da reserva: ").append(id);
        stringBuilder.append("\nNome do hóspeede: ").append(guest.getName());
        stringBuilder.append("\nNúmero do quarto: ").append(bedroom.getBedroomNumber());
        stringBuilder.append("\nData de Check-in: ").append(checkIn.format(dateTimeFormatter));
        stringBuilder.append("\nData de Check-out: ").append(checkOut.format(dateTimeFormatter));
        return stringBuilder;
    }

    public StringBuilder stringBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nNúmero do quarto: ").append(bedroom.getBedroomNumber());
        stringBuilder.append("\nNome do hóspede: ").append(guest.getName());
        stringBuilder.append("\nQuantidade de crianças: ").append(children);
        stringBuilder.append("\nQuantidade de adultos: ").append(adults);
        stringBuilder.append("\nData de Check-in: ").append(checkIn.format(dateTimeFormatter));
        stringBuilder.append("\nData de Check-out: ").append(checkOut.format(dateTimeFormatter));
        stringBuilder.append("\nValor total da estádia: R$ ").append(stayValue);
        return stringBuilder;
    }
}
