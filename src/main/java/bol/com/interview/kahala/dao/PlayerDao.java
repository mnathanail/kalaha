package bol.com.interview.kahala.dao;

import bol.com.interview.kahala.model.Board;
import bol.com.interview.kahala.model.Player;
import bol.com.interview.kahala.model.Winner;
import bol.com.interview.kahala.utils.Utils;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerDao implements IPlayerDao{
    private Player nextPlayer;

    @Override
    public Player playFirst() {
        int r = (int) Math.round(Math.random());
        Player player = getPlayer()[r];
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
    public Player[] getPlayer(){
        Player[] p = new Player[2];
        p[0] = new Player(0);
        p[1] = new Player(1);
        return p;
    }

    @Override
    public Winner getWinner() {
        Board board = Board.getInstance();
        boolean a = Utils.allPitsEmpty(board.getNorthPits());
        boolean b = Utils.allPitsEmpty(board.getSouthPits());
        if(a||b){
            int player1 = board.getNorthKahala().getScore();
            int player2 = board.getSouthKahala().getScore();
            String message = "";
            Player winner = null;
            if(player1 == player2){
                message= "Tie!";
            }
            else if(player1 > player2){
                message= "Player 1 wins";
                winner = getPlayer()[0];
            }
            else {
                message= "Player 2 wins";
                winner = getPlayer()[1];
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
