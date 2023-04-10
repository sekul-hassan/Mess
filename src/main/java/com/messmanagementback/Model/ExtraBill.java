package com.messmanagementback.Model;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class ExtraBill{
    @Id
    @Nonnull
    @Column(length = 100)
    private String messId;
    private Double wifiBill;
    private Double fixedMeal;
    private Double khalaBill;

    public ExtraBill() {

    }

    public ExtraBill(String messId, Double wifiBill, Double fixedMeal, Double khalaBill) {
        this.messId = messId;
        this.wifiBill = wifiBill;
        this.fixedMeal = fixedMeal;
        this.khalaBill = khalaBill;
    }

    public String getMessId() {
        return messId;
    }

    public void setMessId(String messId) {
        this.messId = messId;
    }

    public Double getWifiBill() {
        return wifiBill;
    }

    public void setWifiBill(Double wifiBill) {
        this.wifiBill = wifiBill;
    }

    public Double getFixedMeal() {
        return fixedMeal;
    }

    public void setFixedMeal(Double fixedMeal) {
        this.fixedMeal = fixedMeal;
    }

    public Double getKhalaBill() {
        return khalaBill;
    }

    public void setKhalaBill(Double khalaBill) {
        this.khalaBill = khalaBill;
    }
}
