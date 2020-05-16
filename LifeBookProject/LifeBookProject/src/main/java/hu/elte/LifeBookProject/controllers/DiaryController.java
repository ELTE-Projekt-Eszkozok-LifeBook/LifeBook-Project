package hu.elte.LifeBookProject.controllers;

import hu.elte.LifeBookProject.entities.Diary;
import hu.elte.LifeBookProject.repositories.DiaryRepository;
import java.sql.Date;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
@RequestMapping("diary")
public class DiaryController {
    
    @Autowired
    private DiaryRepository diaryRepository;
    
    //összes naplóbejegyzés lekérése
    @GetMapping("")
    public ResponseEntity<Iterable<Diary>> getAll() {
        List<Diary> entries = diaryRepository.findAllEntries();
        return new ResponseEntity(entries, HttpStatus.OK);
    }
    
    //naplóbejegyzések lekérése dátum szerint
    @GetMapping("/date/{date}")
    public ResponseEntity<Iterable<Diary>> getByDate(@PathVariable Date date) {
        List<Diary> entries = diaryRepository.getPostsByDate(date);
        return new ResponseEntity(entries, HttpStatus.OK);
    }
    
    //naplóbejegyzések lekérése hangulat alapján
    @GetMapping("/mood/{mood}")
    public ResponseEntity<Iterable<Diary>> getByMood(@PathVariable String mood) {
        List<Diary> entries = diaryRepository.getPostsByMood(mood);
        return new ResponseEntity(entries, HttpStatus.OK);
    }
    
    //új naplóbejegyzés felvitele
    @PostMapping("")
    public ResponseEntity<Diary> post(@RequestBody Diary entry) {
        return ResponseEntity.ok(diaryRepository.save(entry));
    }
    
    //naplóbejegyzés törlése id alapján
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Diary> delete(@PathVariable Long id) {
        Optional<Diary> entry = diaryRepository.findById(id);
        if (!entry.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        diaryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
    //naplóbejegyzés módosítása id alapján
    @PutMapping("/update/{id}")
    public ResponseEntity<Diary> update(@PathVariable Long id, @RequestBody Diary newEntry) {
        Optional<Diary> entry = diaryRepository.findById(id);
        if (!entry.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        entry.get().setText(newEntry.getText());
        entry.get().setCurrentMood(newEntry.getCurrentMood());
        return ResponseEntity.ok(diaryRepository.save(entry.get()));
    }
}
