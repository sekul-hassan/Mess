package com.messmanagementback.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

}
