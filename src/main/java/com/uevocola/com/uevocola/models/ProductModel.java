package com.uevocola.com.uevocola.models;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.UUID;
import jakarta.persistence.*;


@Entity
@Table(name="tb_products")
public class ProductModel implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID idproduct;
   private String name;
   private BigDecimal value;


    public UUID getIdproduct() {
        return this.idproduct;
    }

    public void setIdproduct(UUID idproduct) {
        this.idproduct = idproduct;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return this.value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
   
}
