package bol.com.interview.kalaha.service;

import bol.com.interview.kalaha.model.Player;
import bol.com.interview.kalaha.model.Winner;

public interface IPlayerService {

    public Player playFirst();

    public Player playNext();

    public Winner winner();
}
