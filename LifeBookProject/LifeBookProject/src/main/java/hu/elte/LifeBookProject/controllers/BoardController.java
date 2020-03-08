package hu.elte.LifeBookProject.controllers;

import hu.elte.LifeBookProject.repositories.BoardRepository;
import hu.elte.LifeBookProject.entities.Board;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@CrossOrigin
@RestController
@RequestMapping("board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("")
    public ResponseEntity<Iterable<Board>> getAll(){
        return new ResponseEntity<Iterable<Board>>(boardRepository.findAll(), HttpStatus.OK);
    }

    //todo: get and update checked by id

    //todo: get according to categories

    //todo: get and update important

    //todo: post todo

    //todo: get stuff in sequence (not checked in the begining, checked in tha back)

    //todo: delete todo

    

}