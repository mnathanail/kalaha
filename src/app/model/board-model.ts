import {BigPitModel} from './big-pit-model';
import {PitModel} from './pit-model';

export interface BoardModel{
    southKalaha: BigPitModel;
    northKalaha: BigPitModel;
    northPits: PitModel[];
    southPits: PitModel[];
    winner: number;
}

