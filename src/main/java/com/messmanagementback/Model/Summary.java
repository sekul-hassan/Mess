package com.messmanagementback.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Summary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long addTk;
    private Long totalCost;
    private Double totalMeal;
    private Double Khala;
    private Double wifi;
    private Double fixed;
    private Double mealRate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "messId")
    private MessInfo messInfo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
}
