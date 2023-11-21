package br.com.sistema.DAO;

import br.com.sistema.model.Tier;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

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
    @Column(name = "nivel")
    private Tier tier;


}
