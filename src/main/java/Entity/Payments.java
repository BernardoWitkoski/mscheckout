package Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "payments")
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private int discount;

    @Column(nullable = false)
    private boolean status;

}
