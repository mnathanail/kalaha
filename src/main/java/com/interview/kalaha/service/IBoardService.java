package com.interview.kalaha.service;

import com.interview.kalaha.model.Board;

public interface IBoardService {
    public Board initializeBoard();

    public Board sowStones(int pit, int player);

}
