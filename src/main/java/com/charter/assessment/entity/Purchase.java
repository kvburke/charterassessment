package com.charter.assessment.entity;

import com.charter.assessment.repository.CustomerRepository;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name="Purchase")
public class Purchase {

    @Id
    @SequenceGenerator(
            name="purchase_sequence",
            sequenceName = "purchase_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "purchase_sequence"
    )
    @Column(
            name="id",
            updatable = false
    )
    private Long id;

    @Column(
            name="name",
            nullable= false
    )
    private String name;

    @Column(
            name="price",
            nullable= false
    )
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(
            name= "customer_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name= "customer_order_fk"
            )
    )
    private Customer customer;

    public Purchase(String name, BigDecimal price, Customer customer) {
        this.name = name;
        this.price = price;
        this.customer= customer;
    }

    public Purchase() {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", customer=" + customer +
                '}';
    }
}
