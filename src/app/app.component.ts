import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {GameService} from './service/game.service';
import {switchMap, takeUntil} from 'rxjs/operators';
import {Subject} from 'rxjs';
import {ToastService} from './service/toast.service';
import {Messages} from './messages/messages';
import {BoardModel} from './model/board-model';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {

    destroy$ = new Subject();
    board: BoardModel;
    loaded = false;
    active: number;
    show: boolean;

    constructor(private http: HttpClient,
                private gameService: GameService,
                private toastService: ToastService) {
    }

    ngOnInit(): void {
        this.start();
    }

    start(): void {
        this.initGame();
        this.firstPlayer();
    }

    ngOnDestroy(): void {
        this.destroy$.next();
        this.destroy$.unsubscribe();
    }

    initGame(): void {
        this.gameService.initializeGame()
            .pipe(takeUntil(this.destroy$))
            .subscribe(
                (value) => {
                    this.board = value;
                    this.loaded = true;
                }
            );
    }

    firstPlayer(): void {
        this.gameService.firstPlayer()
            .pipe(takeUntil(this.destroy$))
            .subscribe(
                (value) => {
                    this.active = value.id;
                }
            );
    }

    sow(pit: any, player: number): void | boolean {
        if (player > 1 || player < 0) {
            this.toastService.show(Messages.INVALID_PLAYER,
                {classname: 'bg-danger text-light', delay: 2000, autohide: true});
            return false;
        }
        if (this.active !== player) {
            this.toastService.show(Messages.NOT_YOUR_TURN,
                {classname: 'bg-danger text-light', delay: 2000, autohide: true});
            return false;
        }
        if (pit.value === 0) {
            this.toastService.show(Messages.CLICK_ZERO, {
                classname: 'bg-danger text-light',
                delay: 3000,
                autohide: true
            });
            return false;
        }

        this.gameService.sow(pit.pos, player)
            .pipe(switchMap(value => {
                this.board = value;
                if (this.board.winner) {
                    return this.gameService.winner();
                }
                return this.gameService.playNext();
            }))
            .subscribe(value => {

                if ('id' in value) {
                    this.active = value.id;
                }
                if ('message' in value) {
                    this.toastService.show(value.message, {
                        classname: 'bg-success text-light',
                        delay: 3000,
                        autohide: true
                    });
                }
            });
    }

}
