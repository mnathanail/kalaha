package bol.com.interview.kalaha.dao;

import bol.com.interview.kalaha.model.Board;
import bol.com.interview.kalaha.model.Player;
import bol.com.interview.kalaha.model.Winner;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerDao implements IPlayerDao{
    private Player nextPlayer;

    @Override
    public Player playFirst() {
        // system randomly decides first player
        int r = (int) Math.round(Math.random());
        Player player = getPlayers()[r];
        setNextPlayer(player);
        return player;
    }

    @Override
    public Player getPlayNext() {
        return getNextPlayer();
    }

    @Override
    public void setPlayNext(Player nextPlayer) {
        setNextPlayer(nextPlayer);
    }

    @Override
    public Player[] getPlayers(){
        //create two players
        Player[] p = new Player[2];
        p[0] = new Player(0);
        p[1] = new Player(1);
        return p;
    }

    @Override
    public Player getPlayerById(int id){
        return getPlayers()[id];
    }

    @Override
    public Winner getWinner() {
        Board board = Board.getInstance();
        //check if at least one list(area) has no stones in their pits
        if(board.emptyPitExists()){
            int player1 = board.getNorthKalaha().getScore();
            int player2 = board.getSouthKalaha().getScore();
            String message;
            Player winner = null;
            if(player1 == player2){
                message= "It's a tie!";
            }
            else if(player1 > player2){
                message= "Victory, Player 1 wins";
                winner = getPlayers()[0];
            }
            else {
                message= "Victory, Player 2 wins";
                winner = getPlayers()[1];
            }

            return new Winner(message, winner);
        }
        return null;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }
}
