package bol.com.interview.kalaha.service;

import bol.com.interview.kalaha.model.Board;

public interface IBoardService {
    public Board initializeBoard();

    public Board sowStones(int pit, int player);

}
