package com.messmanagementback.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Summary {
    @Id
    private String messId;
    private Long addTk;
    private Long totalCost;
    private Double totalMeal;
    private Double Khala;
    private Double wifi;
    private Double fixed;
    private Double mealRate;
}
