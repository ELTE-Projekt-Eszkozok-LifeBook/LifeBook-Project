
package hu.elte.LifeBookProject.controllers;

import hu.elte.LifeBookProject.entities.FinancialStats;
import hu.elte.LifeBookProject.repositories.FinancialStatsRepository;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
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
@RequestMapping("financial")
public class FinancialStatsController {

    @Autowired
    private FinancialStatsRepository financialStatRepo;
    
    //összes pénzmozgások lekérése
    @GetMapping("")
    public ResponseEntity<Iterable<FinancialStats>> getAll() {
        return new ResponseEntity(financialStatRepo.findAll(), HttpStatus.OK);
    }
    
    //pénzmozgások lekérése kategória szerint
    @GetMapping("/category/{category}")
    public ResponseEntity<Iterable<FinancialStats>> getByCategory(@PathVariable String category) {
        List<FinancialStats> stats = financialStatRepo.getByCategory(category);
        return new ResponseEntity(stats, HttpStatus.OK);
    }
    
    //pénzmozgások lekérése dátum szerint
    @GetMapping("/date/{date}")
    public ResponseEntity<Iterable<FinancialStats>> getByDate(@PathVariable Date date) {
        List<FinancialStats> stats = financialStatRepo.getByDate(date);
        return new ResponseEntity(stats, HttpStatus.OK);
    }
    
    //pénzmozgások lekérése dátum szerint
    @GetMapping("/month/{month}")
    public ResponseEntity<Iterable<FinancialStats>> getByMonth(@PathVariable Integer month) {
        List<FinancialStats> stats = financialStatRepo.getByMonth(month);
        return new ResponseEntity(stats, HttpStatus.OK);
    }
    
    //költségek összegének lekérése
    @GetMapping("/costs")
    public ResponseEntity<Iterable<FinancialStats>> getCosts() {
        Integer costs = financialStatRepo.getCosts();
        return new ResponseEntity(costs, HttpStatus.OK);
    }
    
    @GetMapping("/costs/{category}")
    public ResponseEntity<Iterable<FinancialStats>> getCostsByCategory(@PathVariable String category) {
        Integer costs = financialStatRepo.getCostsByCategory(category);
        return new ResponseEntity(costs, HttpStatus.OK);
    }
    
    //új pénzmozgás felvitele
    @PostMapping("")
    public ResponseEntity<FinancialStats> post(@RequestBody FinancialStats stat) {
        return ResponseEntity.ok(financialStatRepo.save(stat));
    }
    
    //pénzmozgás törlése id alapján
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<FinancialStats> delete(@PathVariable Long id) {
        Optional<FinancialStats> entry = financialStatRepo.findById(id);
        if (!entry.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        financialStatRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
    //pénzmozgás módosítása id alapján
    @PutMapping("/update/{id}")
    public ResponseEntity<FinancialStats> update(@PathVariable Long id, @RequestBody FinancialStats newStat) {
        Optional<FinancialStats> stat = financialStatRepo.findById(id);
        if (!stat.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        stat.get().setDate(newStat.getDate());
        stat.get().setCategory(newStat.getCategory());
        stat.get().setAmount(newStat.getAmount());
        stat.get().setDescription(newStat.getDescription());
        return ResponseEntity.ok(financialStatRepo.save(stat.get()));
    }
}
