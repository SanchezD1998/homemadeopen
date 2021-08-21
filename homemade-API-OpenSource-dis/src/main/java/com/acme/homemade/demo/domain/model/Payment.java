package com.acme.homemade.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "Payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private  Long cardNumber;

    @NotNull
    private String cardName;

    @NotNull
    private  String paymentDetail;

    @NotNull
    private Date  dateTime;

    @NotNull
    private Float total;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userNoChef_id", nullable = false)
    @JsonIgnore
    private UserNoChef userNoChef;
}
