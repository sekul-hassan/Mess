package com.messmanagementback.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessInfo {
    @Id
    private String messId;
    private String messName;
    private String messEmail;
    private String messPassword;
}
