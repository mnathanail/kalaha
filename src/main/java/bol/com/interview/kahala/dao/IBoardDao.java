package bol.com.interview.kahala.dao;

import bol.com.interview.kahala.model.BigPit;
import bol.com.interview.kahala.model.Board;
import bol.com.interview.kahala.model.Pit;
import bol.com.interview.kahala.model.Player;

import java.util.List;
import java.util.Map;

public interface IBoardDao {
    public Board initializeBoard();
    public Map<Integer, BigPit> getBigPit();
    public Map<Integer, List<Pit>> getPitList();
    public boolean haveWinner(Board board);
}
