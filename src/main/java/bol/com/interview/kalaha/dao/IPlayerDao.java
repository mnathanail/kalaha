package bol.com.interview.kalaha.dao;

import bol.com.interview.kalaha.model.Player;
import bol.com.interview.kalaha.model.Winner;

public interface IPlayerDao {
    public Player playFirst();
    public Player getPlayNext();
    public void setPlayNext(Player player);
    public Player[] getPlayers();
    public Winner getWinner();

    Player getPlayerById(int player);
}
