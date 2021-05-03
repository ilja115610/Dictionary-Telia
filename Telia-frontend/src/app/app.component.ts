import {Component, OnInit} from '@angular/core';
import { Location } from '@angular/common';
import {NavigationEnd, NavigationStart, Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'Telia-frontend';

translationMode: boolean = true;

constructor(private location: Location, private router : Router) {
}

ngOnInit (): void {
  this.setMode()
}

setMode() {

    this.router.events.subscribe(value => {
      if(value instanceof NavigationStart || value instanceof NavigationEnd){
        this.translationMode = value['url']==='/translation';
        if(value['urlAfterRedirects']==='/translation'){
          this.translationMode = true;
        }
      }

  });




}

}
