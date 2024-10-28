package com.messmanagementback.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

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
    @OneToMany(mappedBy = "messInfo", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Member> members;
    @OneToMany(mappedBy = "messInfo", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Cost> costs;
    @OneToMany(mappedBy = "messInfo", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<ExtraBill> extraBills;

}
