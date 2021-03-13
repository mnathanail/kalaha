package bol.com.interview.kahala.service;

import bol.com.interview.kahala.dao.IPlayerDao;
import bol.com.interview.kahala.model.Player;
import bol.com.interview.kahala.model.Winner;
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
