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
@Table(name = "diary")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Diary extends BaseEntity {

    @Column
    @NotNull
    private String text;

    @Column
    private String image;

    @Column
    private String video;

    @Column
    @NotNull
    private String currentMood;

    @Column
    @NotNull
    private Date date;
}
