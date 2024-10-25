package com.messmanagementback.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name="";
    private String email="";
    private String phone="";
    private Double addTk=0.0;
    private Double backTk=0.0;
    private Double totalMeal=0.0;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "messId")
    private MessInfo messInfo;
}
