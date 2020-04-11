package hu.elte.LifeBookProject.entities;

import hu.elte.LifeBookProject.enums.Frequency;

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
import java.sql.Time;


@Entity
@Table(name = "timetable")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class TimeTable extends BaseEntity {

    @Column
    @NotNull
    private String event;
    
    @Column
    @NotNull
    private String frequency;
    
    @Column
    @NotNull
    private Date date;
    
    @Column
    @NotNull
    private Time time;
    
    //megjegyzés
    @Column
    private String note;
    
}