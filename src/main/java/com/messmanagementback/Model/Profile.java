package com.messmanagementback.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String profilePic;
    private String coverPic;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "messId")
    private MessInfo messInfo;
}
