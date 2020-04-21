package hu.elte.LifeBookProject.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.sql.Date;


@Entity
@Table(name = "financialstats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FinancialStats extends BaseEntity {

    @Column
    @NotNull
    private String category;
    
    @Column
    @NotNull
    private Date date;
    
    @Column
    @NotNull
    private Integer amount;
    
    //leírás, megjegyzés
    @Column
    private String description;
}
