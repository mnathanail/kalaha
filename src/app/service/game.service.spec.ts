import {getTestBed, TestBed} from '@angular/core/testing';

import {GameService} from './game.service';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {BoardModel} from '../model/board-model';
import {PlayerModel} from '../model/player-model';

describe('GameService', () => {
    let injector: TestBed;
    let service: GameService;
    let httpMock: HttpTestingController;
    const initGameObj = {
        southKahala: {score: 0},
        northKahala: {score: 0},
        northPits: [
            {pos: 0, value: 6}, {pos: 1, value: 6}, {pos: 2, value: 6},
            {pos: 3, value: 6}, {pos: 4, value: 6}, {pos: 5, value: 6}
        ],
        southPits: [
            {pos: 0, value: 6}, {pos: 1, value: 6}, {pos: 2, value: 6},
            {pos: 3, value: 6}, {pos: 4, value: 6}, {pos: 5, value: 6}
        ],
        winner: false
    } as unknown as BoardModel;

    const afterSowGameObj = {
        southKahala: {score: 0},
        northKahala: {score: 1},
        northPits: [
            {pos: 0, value: 7}, {pos: 1, value: 7}, {pos: 2, value: 7},
            {pos: 3, value: 7}, {pos: 4, value: 7}, {pos: 5, value: 0}
        ],
        southPits: [
            {pos: 0, value: 6}, {pos: 1, value: 6}, {pos: 2, value: 6},
            {pos: 3, value: 6}, {pos: 4, value: 6}, {pos: 5, value: 6}
        ],
        winner: false
    } as unknown as BoardModel;


    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [HttpClientTestingModule],
            providers: [
                GameService
            ]
        });
        injector = getTestBed();
        service = injector.get(GameService);
        httpMock = injector.get(HttpTestingController);
    });

    afterEach(() => {
        httpMock.verify();
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });

    it('should initialize game ', () => {
        service.initializeGame().subscribe(data => {
            expect(data).toEqual(initGameObj);
        });

        const req = httpMock.expectOne('http://localhost:8080/start-game');
        expect(req.request.method).toBe('GET');
        req.flush(initGameObj);
    });

    it('should return the first player ', () => {
        const player = {id: 0} as PlayerModel;
        service.firstPlayer().subscribe(data => {
            expect(data).toEqual(player);
        });

        const req = httpMock.expectOne('http://localhost:8080/first-player');
        expect(req.request.method).toBe('GET');
        req.flush(player);
    });

    it('should return next player ', () => {
        const player = {id: 0} as PlayerModel;
        service.playNext().subscribe(data => {
            expect(data).toEqual(player);
        });

        const req = httpMock.expectOne('http://localhost:8080/next-player');
        expect(req.request.method).toBe('GET');
        req.flush(player);
    });

    it('should return the winner ', () => {
        const winner = {message: 'Victory, Player 1 wins', winner: {id: 0}};
        service.winner().subscribe(data => {
            expect(data).toEqual(winner);
        });

        const req = httpMock.expectOne('http://localhost:8080/winner');
        expect(req.request.method).toBe('GET');
        req.flush(winner);
    });


    it('should return board with changes ', () => {
        service.sow('1', 0).subscribe(data => {
            expect(data).toEqual(afterSowGameObj);
        });

        const req = httpMock.expectOne('http://localhost:8080/sow-stones?pit=1&player=0');
        expect(req.request.method).toEqual('GET');
        req.flush(afterSowGameObj);
    });
});
