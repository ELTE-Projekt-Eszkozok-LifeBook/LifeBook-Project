
package hu.elte.LifeBookProject.controllers;

import hu.elte.LifeBookProject.entities.SportActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import hu.elte.LifeBookProject.repositories.SportActivityRepository;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
@RequestMapping("sportactivity")
public class SportActivityController {
    
    @Autowired
    private SportActivityRepository sportActivityRepo;
    
    //összes sportolás lekérése
    @GetMapping("")
    public ResponseEntity<Iterable<SportActivity>> getAll() {
        return new ResponseEntity(sportActivityRepo.findAll(), HttpStatus.OK);
    }
    
    //sportolási tevékenység lekérése elnevezés alapján
    @GetMapping("/name/{name}")
    public ResponseEntity<SportActivity> getByName(@PathVariable String name) {
        SportActivity sportActivity = sportActivityRepo.getByName(name);
        if (sportActivity == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(sportActivity, HttpStatus.OK);
    }
    
    //sportolási tevékenységek lekérése gyakoriság alapján
    @GetMapping("/regularity/{regularity}")
    public ResponseEntity<Iterable<SportActivity>> getByRegularity(@PathVariable String regularity) {
        List<SportActivity> sportActivities = sportActivityRepo.getByRegularity(regularity);
        return new ResponseEntity(sportActivities, HttpStatus.OK);
    }
    
    //sportolási tevékenységek lekérése attól függően, hogy egyesületnél van végezve, vagy sem
    @GetMapping("/{official}")
    public ResponseEntity<Iterable<SportActivity>> getByOfficiality(@PathVariable String official) {
        List<SportActivity> sportActivities = null;
        if (official.equals("officials")) {
            sportActivities = sportActivityRepo.getByOfficiality(true);
        } else if (official.equals("notofficials")) {
            sportActivities = sportActivityRepo.getByOfficiality(false);
        }
        return new ResponseEntity(sportActivities, HttpStatus.OK);
    }
    
    //új sportolási tevékenységek felvitele
    @PostMapping("")
    public ResponseEntity<SportActivity> post(@RequestBody SportActivity sportActivity) {
        if (sportActivityRepo.getByName(sportActivity.getName()) == null) {
            return ResponseEntity.ok(sportActivityRepo.save(sportActivity));
        }
        return ResponseEntity.ok().build();
    }
    
    //sportolási tevékenység törlése elnevezés alapján
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<SportActivity> delete(@PathVariable String name) {
        SportActivity sportActivity = sportActivityRepo.getByName(name);
        if (sportActivity == null) {
            return ResponseEntity.notFound().build();
        }
        sportActivityRepo.deleteById(sportActivity.getId());
        return ResponseEntity.ok().build();
    }
    
    //sportolási tevékenység módostása elnevezés alapján
    @PutMapping("/update/{name}")
    public ResponseEntity<SportActivity> update(@PathVariable String name, @RequestBody SportActivity newActivity) {
        SportActivity sportActivity = sportActivityRepo.getByName(name);
        if (sportActivity == null) {
            return ResponseEntity.notFound().build();
        }
        sportActivity.setRegularity(newActivity.getRegularity());
        sportActivity.setDuration(newActivity.getDuration());
        sportActivity.setStartTime(newActivity.getStartTime());
        sportActivity.setOfficial(newActivity.isOfficial());
        return ResponseEntity.ok(sportActivityRepo.save(sportActivity));
    }
}
