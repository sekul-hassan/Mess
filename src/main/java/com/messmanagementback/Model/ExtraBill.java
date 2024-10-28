package com.messmanagementback.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExtraBill{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double wifiBill;
    private Double fixedMeal;
    private Double khalaBill;
    private Double others;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "messId")
    private MessInfo messInfo;
}
