package com.messmanagementback.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MessInfo {
    @Id
    private String messId;
    private String messName;
    private String messEmail;
    private String messPassword;

    @OneToMany(mappedBy = "messInfo", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Member> members;

    @OneToMany(mappedBy = "messInfo", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Cost> costs;

    @OneToMany(mappedBy = "messInfo", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ExtraBill> extraBills;

    @OneToOne(mappedBy = "messInfo", fetch = FetchType.LAZY)
    @JsonIgnore
    private Profile profile;

    @OneToMany(mappedBy = "messInfo", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Summary> summaries;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
}
