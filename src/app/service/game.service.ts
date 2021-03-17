import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Endpoints} from '../endpoint/endpoints';
import {Observable} from 'rxjs';
import {BoardModel} from '../model/board-model';
import {PlayerModel} from '../model/player-model';
import {BoundEventAst} from '@angular/compiler';
import {WinnerModel} from '../model/winner-model';

@Injectable({
    providedIn: 'root'
})
export class GameService {

    constructor(private http: HttpClient) {
    }

    initializeGame(): Observable<BoardModel> {
        return this.http.get<BoardModel>(Endpoints.START_GAME);
    }

    firstPlayer(): Observable<PlayerModel> {
        return this.http.get<PlayerModel>(Endpoints.FIRST_PLAYER);
    }

    sow(pit: string, player: number): Observable<BoardModel> {
        const params = new HttpParams()
            .set('pit', pit)
            .set('player', String(player));
        return this.http.get<BoardModel>(Endpoints.SOW, {params});
    }

    playNext(): Observable<PlayerModel>{
        return this.http.get<PlayerModel>(Endpoints.NEXT_PLAYER);
    }

    winner(): Observable<WinnerModel>{
        return this.http.get<WinnerModel>(Endpoints.WINNER);
    }
}
