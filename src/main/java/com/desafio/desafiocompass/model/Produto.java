package com.desafio.desafiocompass.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @NotNull(message = "Não deve deixar em branco")
    @NotBlank(message = "Não deve deixar em branco")
    private String name;
    @NotNull(message = "Não deve deixar em branco")
    @NotBlank(message = "Não deve deixar em branco")
    private String description;
    @NotNull(message = "Não deve deixar em branco")
    @Min(value = 0, message = "Valor tem que ser maior que zero")
    private Double price;


    public Produto() {
    }

    public Produto(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
