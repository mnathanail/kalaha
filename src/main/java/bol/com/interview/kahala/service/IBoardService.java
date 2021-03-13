package bol.com.interview.kahala.service;

import bol.com.interview.kahala.model.Board;
import bol.com.interview.kahala.model.Player;
import bol.com.interview.kahala.model.Winner;

public interface IBoardService {
    public Board initializeBoard();

    public Board sowStones(int pit, int player);

}
