
package hu.elte.LifeBookProject.entities;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sport")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SportActivity extends BaseEntity {
    
    @Column
    @NotNull
    private String name;
    
    @Column
    @NotNull
    private String regularity;
    
    @Column
    @NotNull
    private double duration;
    
    //mikor kezdte az adott sport tevékenységet
    @Column
    @NotNull
    private Date startTime;
    
    //egyesületnél/konditeremben/hivatalos szerv által biztosított helyen végzi-e az illető a tevékenységet vagy sem
    @Column
    @NotNull
    private boolean isOfficial;
}
