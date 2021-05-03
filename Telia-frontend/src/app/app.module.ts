import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { TranslationComponent } from './components/translation/translation.component';
import { AdditionComponent } from './components/addition/addition.component';
import {RouterModule, Routes} from '@angular/router';
import {HttpClientModule} from '@angular/common/http';
import {ReactiveFormsModule} from '@angular/forms';


const routes: Routes = [

  {path:'translation', component: TranslationComponent },
  {path:'addition', component: AdditionComponent },
  {path:'', redirectTo: '/translation', pathMatch: 'full' },

];

@NgModule({
  declarations: [
    AppComponent,
    TranslationComponent,
    AdditionComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
