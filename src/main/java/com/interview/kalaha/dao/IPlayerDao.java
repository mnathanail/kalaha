package com.interview.kalaha.dao;

import com.interview.kalaha.model.Player;
import com.interview.kalaha.model.Winner;

public interface IPlayerDao {
    public Player playFirst();
    public Player getPlayNext();
    public void setPlayNext(Player player);
    public Player[] getPlayers();
    public Winner getWinner();

    Player getPlayerById(int player);
}
