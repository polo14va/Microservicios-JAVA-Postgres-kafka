package edu.uoc.epcsd.user.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@ToString
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date from;

    @Column(name = "to", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date to;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @ManyToOne
    private User user;

}
