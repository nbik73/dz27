package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Premiere {

    @Id
    @GeneratedValue
    private Integer id;

    private String nameOfThePremiere; //название
    private String description;       //описание премьеры
    private Integer ageCategory;          //возрастная категория
    private Integer quantityOfSeats;      //количество мест

    public Premiere(String nameOfThePremiere, String description, int ageCategory, int quantityOfSeats) {
        this.nameOfThePremiere = nameOfThePremiere;
        this.description = description;
        this.ageCategory = ageCategory;
        this.quantityOfSeats = quantityOfSeats;
    }

    @Override
    public String toString() {
        return "Премьера {" +
                "" + nameOfThePremiere + '\'' +
                ", description='" + description + '\'' +
                ", ageCategory=" + ageCategory +
                ", seatList=" + quantityOfSeats +
                '}';
    }
}
