package bol.com.interview.kalaha.service;

import bol.com.interview.kalaha.dao.IPlayerDao;
import bol.com.interview.kalaha.model.Player;
import bol.com.interview.kalaha.model.Winner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService implements IPlayerService{

    @Autowired
    private IPlayerDao playerDao;

    @Override
    public Player playFirst() {
        return playerDao.playFirst();
    }

    @Override
    public Player playNext() {
        return playerDao.getPlayNext();
    }

    @Override
    public Winner winner() {
        return playerDao.getWinner();
    }
}
