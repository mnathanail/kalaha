package bol.com.interview.kahala.controller;

import bol.com.interview.kahala.model.Board;
import bol.com.interview.kahala.model.Player;
import bol.com.interview.kahala.model.Winner;
import bol.com.interview.kahala.service.IBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.*;

@Validated
@RestController
@CrossOrigin("http://localhost:4200")
public class KahalaController {

    @Autowired
    private IBoardService service;

    @GetMapping("/start-game")
    @ResponseBody
    public Board initBoard(){
        return service.initializeBoard();
    }

    @GetMapping("/first-player")
    @ResponseBody
    public Player playFirst(){
        return service.playFirst();
    }

    @GetMapping("/next-player")
    @ResponseBody
    public Player playNext(){
        return service.playNext();
    }

    @GetMapping("/sow-stones")
    @ResponseBody
    public Board sow(@RequestParam("pit") @Min(0) @Max(5) int pit,
                     @RequestParam("player") @Min(0) @Max(1) int player ){
        return service.sowStones(pit, player);
    }

    @GetMapping("/winner")
    @ResponseBody
    public Winner winner(){
        return service.winner();
    }

}
