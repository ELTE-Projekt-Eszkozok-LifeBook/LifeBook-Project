package hu.elte.LifeBookProject.controllers;

import hu.elte.LifeBookProject.entities.Diary;
import hu.elte.LifeBookProject.repositories.DiaryRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@CrossOrigin
@RestController
@RequestMapping("diary")
public class DiaryController{
    
    @Autowired
    private DiaryRepository diaryRepository;

    @GetMapping("")
    public ResponseEntity<Iterable<Diary>> getAll(){
        return new ResponseEntity<Iterable<Diary>>(diaryRepository.findAll(), HttpStatus.OK);
    }
}