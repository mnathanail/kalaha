import {TestBed} from '@angular/core/testing';

import {GameService} from './game.service';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {shareReplay} from 'rxjs/operators';

describe('GameService', () => {
    let service: GameService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [
                GameService
            ]
        });
        service = TestBed.inject(GameService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });

});
