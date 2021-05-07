import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {SearchService} from '../../services/search-service';
import {Word} from '../../common/word';

@Component({
  selector: 'app-translation',
  templateUrl: './translation.component.html',
  styleUrls: ['./translation.component.css']
})
export class TranslationComponent implements OnInit {

  searchFormGroup: FormGroup;

  word: Word = new Word();

  results: Word [] = [];

  search: string = "";


  constructor(private formBuilder:FormBuilder, private searchService: SearchService) { }

  ngOnInit(): void {
    this.searchFormGroup = this.formBuilder.group({
      inputs: this.formBuilder.group({
        searchWord: new FormControl('', [Validators.required]),
        lang: new FormControl('eng', [Validators.required])
      })

    });

  }

  flag(): boolean {

    return this.searchFormGroup.touched&&this.searchFormGroup.invalid;
  }



  onSubmit() {
    this.word.word = this.searchWord.value.toLowerCase();
    if(this.searchFormGroup.invalid){
      this.searchFormGroup.markAllAsTouched();
      return false;
    }
    this.search = this.searchWord.value.toLowerCase();
    this.searchService.getTranslation(this.searchWord.value.toLowerCase(),this.language.value).subscribe(
      data=>this.results = data
    );
    this.searchFormGroup.markAsUntouched();
    this.searchWord.setValue('')


  }

  get searchWord () {
    return this.searchFormGroup.get('inputs.searchWord');


  }

  get language () {
    return this.searchFormGroup.get('inputs.lang')
  }
}
