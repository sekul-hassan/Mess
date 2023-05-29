package com.messmanagementback.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExtraBill{
    @Id
    private String messId;
    private Double wifiBill;
    private Double fixedMeal;
    private Double khalaBill;
}
