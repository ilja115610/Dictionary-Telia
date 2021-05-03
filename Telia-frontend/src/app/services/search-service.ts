import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Word} from '../common/word';
import {map} from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class SearchService {

  private baseUrl = 'http://localhost:8080/api'

  constructor(private httpClient: HttpClient) { }

  public getTranslation (word: string, lang: string) : Observable<Word []> {

    let url = "";

    const headers = { 'content-type': 'application/json'}

    if(lang === 'eng') {
      url = `${this.baseUrl}/eng?word=${word}`;
    }
    if(lang === 'est'){
      url = `${this.baseUrl}/est?word=${word}`;
    }

    return this.httpClient.get<Word []>(url,{'headers':headers}).pipe(
      map(response=>response)
    )
  }
}
