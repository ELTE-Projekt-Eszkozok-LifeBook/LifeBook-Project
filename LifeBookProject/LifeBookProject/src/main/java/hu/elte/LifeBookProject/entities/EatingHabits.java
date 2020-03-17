
package hu.elte.LifeBookProject.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "eating")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EatingHabits extends BaseEntity{
    
    @Column
    private String food;
    
    @Column
    private String foodType;
    
    @Column
    private String drink;
    
    @Column
    private String drinkType;
    
    @Column
    @NotNull
    private String frequency;
    
}
