package hu.elte.LifeBookProject.entities;

import hu.elte.LifeBookProject.enums.TodoCategory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "todo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class Todo extends BaseEntity{

    @Column
    @NotNull
    private String todoText;

    @Column
    @NotNull
    private boolean checked;

    @Column
    @NotNull
    private boolean important;

    @Column
    @NotNull
    private String category;

}