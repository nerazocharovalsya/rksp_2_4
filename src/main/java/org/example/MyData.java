package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MyData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private Integer amount;
    private Float price;


    public MyData(String title, Integer amount, Float price) {
        this.title = title;
        this.amount = amount;
        this.price = price;
    }
}
