import { Component } from '@angular/core';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'app-history',
    templateUrl: 'history.component.html',
    providers: [NgbModalConfig, NgbModal]
})
export class HistoryComponent {
    constructor(config: NgbModalConfig, private modalService: NgbModal) {
        config.backdrop = 'static';
        config.keyboard = false;
    }

    open(content): void {
        this.modalService.open(content,{ scrollable: true });
    }
}
