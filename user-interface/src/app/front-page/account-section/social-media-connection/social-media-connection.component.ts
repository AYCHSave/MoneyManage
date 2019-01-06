import {animate, state, style, transition, trigger} from "@angular/animations";
import {AccountSection} from "../account-section";
import {Component} from "@angular/core";

@Component({
    selector: 'app-social-media-connection',
    templateUrl: './social-media-connection.component.html',
    styleUrls: ['./social-media-connection.component.css'],
    animations: [
        trigger('showSocial', [
            state('true', style({opacity: 1, transform: 'translateY(0%)'})),
            state('false', style({opacity: 0, transform: 'translateY(25%)'})),
            transition('false => true', animate('1s 2s ease-in'))
        ])
    ]
})
export class SocialMediaConnectionComponent extends AccountSection{

}
