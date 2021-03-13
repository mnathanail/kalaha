package bol.com.interview.kahala.service;

import bol.com.interview.kahala.model.Player;
import bol.com.interview.kahala.model.Winner;

public interface IPlayerService {

    public Player playFirst();

    public Player playNext();

    public Winner winner();
}
