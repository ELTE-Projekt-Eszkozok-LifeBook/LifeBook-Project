
package hu.elte.LifeBookProject.controllers;

import hu.elte.LifeBookProject.entities.EatingHabit;
import hu.elte.LifeBookProject.repositories.EatingHabitRepository;
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
@RequestMapping("eatinghabits")
public class EatingHabitController {
    
    @Autowired
    private EatingHabitRepository eatingHabitRepo;
    
    //összes étkezési szokás lekérése
    @GetMapping("")
    public ResponseEntity<Iterable<EatingHabit>> getAll() {
        return new ResponseEntity(eatingHabitRepo.findAll(), HttpStatus.OK);
    }
    
    //étkezési szokások lekérése típus alapján
    @GetMapping("/type/{type}")
    public ResponseEntity<Iterable<EatingHabit>> getByType(@PathVariable String type) {
        List<EatingHabit> eatingHabits = eatingHabitRepo.getByType(type);
        return new ResponseEntity(eatingHabits, HttpStatus.OK);
    }
    
    //étkezési szokások lekérése aszerint, hogy kajákat, vagy innivalókat szeretnénk
    @GetMapping("/{eating}")
    public ResponseEntity<Iterable<EatingHabit>> getByIsFood(@PathVariable String eating) {
        List<EatingHabit> eatingHabit = null;
        if (eating.equals("food")) {
            eatingHabit = eatingHabitRepo.getByIsFood(true);
        } else if (eating.equals("drink")) {
            eatingHabit = eatingHabitRepo.getByIsFood(false);
        }
        return new ResponseEntity(eatingHabit, HttpStatus.OK);
    }
    
    //új étkezési szokás felvitele
    @PostMapping("")
    public ResponseEntity<EatingHabit> post(@RequestBody EatingHabit eatingHabit) {
        return ResponseEntity.ok(eatingHabitRepo.save(eatingHabit));
    }
    
    //étkezési szokás törlése elnevezés alapján
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<EatingHabit> delete(@PathVariable String name) {
        EatingHabit eatingHabit = eatingHabitRepo.getByName(name);
        if (eatingHabit == null) {
            return ResponseEntity.notFound().build();
        }
        eatingHabitRepo.deleteById(eatingHabit.getId());
        return ResponseEntity.ok().build();
    }
    
    //étkezési szokás módostása elnevezés alapján
    @PutMapping("/update/{name}")
    public ResponseEntity<EatingHabit> update(@PathVariable String name, @RequestBody EatingHabit newHabit) {
        EatingHabit eatingHabit = eatingHabitRepo.getByName(name);
        if (eatingHabit == null) {
            return ResponseEntity.notFound().build();
        }
        eatingHabit.setType(newHabit.getType());
        eatingHabit.setFood(newHabit.isFood());
        eatingHabit.setPortion(newHabit.getPortion());
        eatingHabit.setFrequency(newHabit.getFrequency());
        return ResponseEntity.ok(eatingHabitRepo.save(eatingHabit));
    }
}
