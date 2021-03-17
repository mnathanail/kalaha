import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {NgbModal, NgbModalConfig, NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {ToastComponent} from './toast/toast.component';
import {RulesComponent} from './modal/rules.component';
import {HistoryComponent} from './modal/history.component';

@NgModule({
    declarations: [
        AppComponent,
        ToastComponent,
        RulesComponent,
        HistoryComponent
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        NgbModule
    ],
    providers: [NgbModalConfig, NgbModal],
    bootstrap: [AppComponent]
})
export class AppModule {
}
