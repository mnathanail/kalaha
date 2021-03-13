package bol.com.interview.kahala.service;

import bol.com.interview.kahala.dao.IBoardDao;
import bol.com.interview.kahala.model.Board;
import bol.com.interview.kahala.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;

public class BoardService implements IBoardService{

    @Autowired
    private IBoardDao boardDao;

    @Override
    public Board initializeBoard() {
        return boardDao.initializeBoard();
    }

    @Override
    public Board sowStones(int pit, int player) {
        Board board = Board.getInstance();
        int nextPit = pit;
        int last = 0;
        int pitValue = 0;
        boolean playAgain = false;
        int nextPlayer = 0;
        int stones = boardDao.getPitList().get(player).get(pit).getValue();
        /*if(stones <=0){
            throw new NotFoundException();
            throw custom exception, click on another number! :)
        }*/
        boardDao.getPitList().get(player).get(pit).setValue(0);
        int currentPlayer = player;

        while (stones > 0) {
            nextPit--;
            // means will sow on BigPit(Kahala)
            if (nextPit < 0) {
                // owns it?
                if (currentPlayer == player) {
                    updateKahalaScore(player);
                    stones--;
                }
                // if was last stone, break
                if(stones<=0){
                    last = nextPit;
                    break;
                }
                // if was not the last, index it for the opponent's
                nextPit = 5;
                currentPlayer = currentPlayer == 0 ? 1 : 0;
            }
            pitValue = boardDao.getPitList().get(currentPlayer).get(nextPit).getValue();
            boardDao.getPitList().get(currentPlayer).get(nextPit).setValue(pitValue + 1);
            last = nextPit;
            stones--;
        }

        if (last == -1) {
            //play again
            playAgain = true;
        }
        if (currentPlayer == player && last != -1) {
            int opponent = 0;
            int opponentStones = 0;
            int getCurrentPlayerKahala = 0;
            pitValue = boardDao.getPitList().get(player).get(last).getValue();
            if (pitValue == 1) {
                // who is the opponent
                opponent = currentPlayer == 0 ? 1 : 0;
                // get their stones
                opponentStones = boardDao.getPitList().get(opponent).get(5 - last).getValue();
                // get and set BigPit (Kahala) score
                getCurrentPlayerKahala = boardDao.getBigPit().get(player).getScore();
                boardDao.getBigPit().get(player).setScore(getCurrentPlayerKahala + opponentStones + 1);
                // set cell to 0
                boardDao.getPitList().get(opponent).get(5 - last).setValue(0);
                // and for current
                boardDao.getPitList().get(player).get(last).setValue(0);
                // play again
                playAgain = true;
            }
        }

        if (playAgain) {
            nextPlayer = player;
        }
        else{
            nextPlayer = player == 0 ? 1 : 0;
        }

        playerDao.setPlayNext(playerDao.getPlayer()[nextPlayer]);
        boolean haveWinner = checkIfAnyAreaIsEmpty(board);

        if(haveWinner){
            board.setWinner(true);
            Utils.updateBoardWin(board);
        }

        return board;
    }

    private void updateKahalaScore(int player){
        int kahalaValue = boardDao.getBigPit().get(player).getScore();
        boardDao.getBigPit().get(player).setScore(kahalaValue + 1);
    }

    private boolean checkIfAnyAreaIsEmpty(Board board){
        boolean a = Utils.allPitsEmpty(board.getNorthPits());
        boolean b = Utils.allPitsEmpty(board.getSouthPits());
        boolean c = a||b;
        System.out.println("It works:" + a + " " + b + " result " +  c);
        return a || b;
    }

}
