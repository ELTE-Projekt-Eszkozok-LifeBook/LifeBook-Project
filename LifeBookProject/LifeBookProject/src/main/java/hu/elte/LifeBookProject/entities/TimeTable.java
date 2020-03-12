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


@Entity
@Table(name = "timetable")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class TimeTable extends BaseEntity {

    @Column
    private String event;
    
    @Column
    @Enumerated(EnumType.STRING)
    private Frequency frequency;
    
    
    @Column
    private Date date;
    
    //TODO
    /*@Column
    private Time time;*/
}