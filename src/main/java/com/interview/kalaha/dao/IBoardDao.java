package com.interview.kalaha.dao;

import com.interview.kalaha.model.BigPit;
import com.interview.kalaha.model.Board;
import com.interview.kalaha.model.Pit;

import java.util.List;
import java.util.Map;

public interface IBoardDao {
    public Board initializeBoard();
    public Map<Integer, BigPit> getBigPit();
    public Map<Integer, List<Pit>> getPitList();
    public boolean haveWinner(Board board);
}
