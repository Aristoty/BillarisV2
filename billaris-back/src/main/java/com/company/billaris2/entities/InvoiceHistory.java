package com.company.billaris2.entities;

import jakarta.persistence.*;

@Entity
public class InvoiceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String actionDate;
    private String actionDescription;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
}
