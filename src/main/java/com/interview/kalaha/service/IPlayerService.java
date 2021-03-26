package com.interview.kalaha.service;

import com.interview.kalaha.model.Player;
import com.interview.kalaha.model.Winner;

public interface IPlayerService {

    public Player playFirst();

    public Player playNext();

    public Winner winner();
}
