package bol.com.interview.kahala.controller;

import bol.com.interview.kahala.model.Board;
import bol.com.interview.kahala.model.Player;
import bol.com.interview.kahala.model.Winner;
import bol.com.interview.kahala.service.IBoardService;
import bol.com.interview.kahala.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Validated
@RestController
@CrossOrigin("http://localhost:4200")
public class KahalaController {

    @Autowired
    private IBoardService boardService;

    @Autowired
    private IPlayerService playerService;

    @GetMapping("/start-game")
    @ResponseBody
    public Board initBoard(){
        return boardService.initializeBoard();
    }

    @GetMapping("/first-player")
    @ResponseBody
    public Player playFirst(){
        return playerService.playFirst();
    }

    @GetMapping("/next-player")
    @ResponseBody
    public Player playNext(){
        return playerService.playNext();
    }

    @GetMapping("/sow-stones")
    @ResponseBody
    public Board sow(@Min(value = 0) @Max(value = 5)  @RequestParam(value = "pit", required = true) int pit,
                     @Min(value = 0) @Max(value = 1)  @RequestParam(value = "player", required = true) int player ){
        return boardService.sowStones(pit, player);
    }

    @GetMapping("/winner")
    @ResponseBody
    public Winner winner(){
        return playerService.winner();
    }

}
