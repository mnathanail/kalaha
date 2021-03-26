package com.interview.kalaha.service;

import com.interview.kalaha.dao.IBoardDao;
import com.interview.kalaha.dao.IPlayerDao;
import com.interview.kalaha.model.Board;
import com.interview.kalaha.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService implements IBoardService{

    @Autowired
    private IBoardDao boardDao;

    @Autowired
    private IPlayerDao playerDao;

    @Override
    public Board initializeBoard() {
        return boardDao.initializeBoard();
    }

    private static final int LAST_PIT_INDEX = 5;
    private static final int PLAYER_1 = 0;
    private static final int PLAYER_2 = 1;

    @Override
    public Board sowStones(int pit, int player) {
        Board board = Board.getInstance();
        Player pd = playerDao.getPlayerById(player);
        int playerId = pd.getId();
        int nextPit = pit;
        int last = 0;
        int pitValue = 0;
        boolean playAgain = false;
        int nextPlayer = 0;

        int stones = boardDao.getPitList().get(player).get(pit).getValue();

        boardDao.getPitList().get(player).get(pit).setValue(0);
        int currentPlayer = playerId;

        while (stones > 0) {
            nextPit--;
            // means will sow on BigPit(Kahala)
            if (nextPit < 0) {
                // owns it?
                if (currentPlayer == playerId) {
                    updateKahalaScore(playerId);
                    stones--;
                }
                // if was last stone, break
                if(stones<=0){
                    last = nextPit;
                    break;
                }
                // if was not the last, index it for the opponent's
                nextPit = LAST_PIT_INDEX;
                currentPlayer = currentPlayer == PLAYER_1 ? PLAYER_2 : PLAYER_1;
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
        if (currentPlayer == playerId && last != -1) {
            int opponent = 0;
            int opponentStones = 0;
            int getCurrentPlayerKalaha = 0;
            pitValue = boardDao.getPitList().get(playerId).get(last).getValue();
            if (pitValue == 1) {
                // who is the opponent
                opponent = currentPlayer == PLAYER_1 ? PLAYER_2 : PLAYER_1;
                // get their stones
                opponentStones = boardDao.getPitList().get(opponent).get(5 - last).getValue();
                // get and set BigPit (Kalaha) score
                getCurrentPlayerKalaha = boardDao.getBigPit().get(playerId).getScore();
                boardDao.getBigPit().get(playerId).setScore(getCurrentPlayerKalaha + opponentStones + 1);
                // set cell to 0
                boardDao.getPitList().get(opponent).get(5 - last).setValue(0);
                // and for current
                boardDao.getPitList().get(playerId).get(last).setValue(0);
                // play again
                playAgain = false;
            }
        }

        if (playAgain) {
            nextPlayer = playerId;
        }
        else{
            nextPlayer = playerId == PLAYER_1 ? PLAYER_2 : PLAYER_1;
        }

        playerDao.setPlayNext(playerDao.getPlayerById(nextPlayer));
        boolean haveWinner = boardDao.haveWinner(board);

        if(haveWinner){
            board.setWinner(true);
            board.calculateScore();
            board.emptyAllPits();
        }

        return board;
    }

    private void updateKahalaScore(int player){
        int kahalaValue = boardDao.getBigPit().get(player).getScore();
        boardDao.getBigPit().get(player).setScore(kahalaValue + 1);
    }

}
