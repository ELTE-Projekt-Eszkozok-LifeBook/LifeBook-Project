
package hu.elte.LifeBookProject.entities;

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
public class EatingHabit extends BaseEntity {
    
    @Column
    @NotNull
    private String name;
    
    @Column
    @NotNull
    private String type;
    
    @Column
    @NotNull
    private boolean isFood;
    
    @Column
    @NotNull
    private String frequency;
    
    @Column
    private String portion;
}
