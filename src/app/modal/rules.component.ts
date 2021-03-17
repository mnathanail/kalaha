import { Component } from '@angular/core';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'app-rules',
    templateUrl: 'rules.component.html',
    providers: [NgbModalConfig, NgbModal]
})
export class RulesComponent {
    constructor(config: NgbModalConfig, private modalService: NgbModal) {
        // customize default values of modals used by this component tree
        config.backdrop = 'static';
        config.keyboard = false;
    }

    open(content) {
        this.modalService.open(content);
    }
}
