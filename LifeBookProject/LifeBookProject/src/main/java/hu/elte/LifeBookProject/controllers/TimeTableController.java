
package hu.elte.LifeBookProject.controllers;

import hu.elte.LifeBookProject.entities.TimeTable;
import hu.elte.LifeBookProject.repositories.TimeTableRepository;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("timetable")
public class TimeTableController {
    
    @Autowired
    private TimeTableRepository timeTableRepo;
    
    //összes esemény lekérése
    @GetMapping("")
    public ResponseEntity<Iterable<TimeTable>> getAll() {
        return new ResponseEntity(timeTableRepo.findAll(), HttpStatus.OK);
    }
    
    //események lekérése dátum szerint
    @GetMapping("/date/{date1}/{date2}")
    public ResponseEntity<Iterable<TimeTable>> getByDate(@PathVariable Date date1, @PathVariable Date date2) {
        List<TimeTable> events = timeTableRepo.getByDate(date1, date2);
        return new ResponseEntity(events, HttpStatus.OK);
    }
    
    //új esemény felvitele
    @PostMapping("")
    public ResponseEntity<TimeTable> post(@RequestBody TimeTable event) {
        return ResponseEntity.ok(timeTableRepo.save(event));
    }
    
    //esemény törlése név alapján
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<TimeTable> delete(@PathVariable String name) {
        TimeTable event = timeTableRepo.getByName(name);
        if (event == null) {
            return ResponseEntity.notFound().build();
        }
        timeTableRepo.deleteById(event.getId());
        return ResponseEntity.ok().build();
    }
    
    //esemény módosítása id alapján
    @PutMapping("/update/{name}")
    public ResponseEntity<TimeTable> update(@PathVariable String name, @RequestBody TimeTable newEvent) {
        TimeTable event = timeTableRepo.getByName(name);
        if (event == null) {
            return ResponseEntity.notFound().build();
        }
        event.setEvent(newEvent.getEvent());
        event.setFrequency(newEvent.getFrequency());
        event.setDate(newEvent.getDate());
        event.setNote(newEvent.getNote());
        return ResponseEntity.ok(timeTableRepo.save(event));
    }
}
