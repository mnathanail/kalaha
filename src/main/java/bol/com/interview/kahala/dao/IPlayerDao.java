package bol.com.interview.kahala.dao;

import bol.com.interview.kahala.model.Player;
import bol.com.interview.kahala.model.Winner;

public interface IPlayerDao {
    public Player playFirst();
    public Player getPlayNext();
    public void setPlayNext(Player player);
    public Player[] getPlayers();
    public Winner getWinner();

    Player getPlayerById(int player);
}
