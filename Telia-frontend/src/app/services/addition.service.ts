import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Word} from '../common/word';
import {map} from 'rxjs/operators';
import {Response} from '../common/response';

@Injectable({
  providedIn: 'root'
})
export class AdditionService {

  private baseUrl = 'http://localhost:8080/api'

  constructor(private httpClient: HttpClient) { }

  public addNewWord ( lang: string, word: Word) : Observable<any> {

    let url = "";

    const headers = { 'content-type': 'application/json'}

    if(lang === 'eng') {
      url = `${this.baseUrl}/${lang}`;
    }
    if(lang === 'est'){
      url = `${this.baseUrl}/${lang}`;
    }

    const body = JSON.stringify(word);


    return this.httpClient.post(url,body,{'headers':headers}).pipe(
      map(response=>response)
    )
  }

  }

