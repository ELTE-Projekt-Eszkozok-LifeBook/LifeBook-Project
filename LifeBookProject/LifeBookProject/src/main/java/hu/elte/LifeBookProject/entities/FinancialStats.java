package hu.elte.LifeBookProject.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import  java.sql.Date;
import hu.elte.LifeBookProject.enums.Category;


@Entity
@Table(name = "financialstats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class FinancialStats extends BaseEntity {

    @Column
    @Enumerated(EnumType.STRING)
    private Category category;
    
    @Column
    private Date date;
    
    @Column
    private Integer amount;
}
