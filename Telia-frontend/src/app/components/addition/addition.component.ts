import { Component, OnInit } from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Word} from '../../common/word';
import {AdditionService} from '../../services/addition.service';
import {Response} from '../../common/response';

@Component({
  selector: 'app-addition',
  templateUrl: './addition.component.html',
  styleUrls: ['./addition.component.css']
})
export class AdditionComponent implements OnInit {

  additionFormGroup : FormGroup;

  word : Word = new Word();

  response: Response = new Response();

  timer: boolean = true;



  constructor(private formBuilder : FormBuilder, private additionService : AdditionService) { }

  ngOnInit(): void {

    this.additionFormGroup = this.formBuilder.group({

        addWord: new FormControl('',Validators.required),
        lang: new FormControl('eng',Validators.required),
        translation: this.formBuilder.array([this.createTranslationFormGroup()])
      })

  }

   createTranslationFormGroup(): FormGroup {
    return this.formBuilder.group({
      'translation': ['', Validators.required]
    })
  }

  getFields():FormArray {
    return  this.additionFormGroup.get('translation') as FormArray
  }

  addField () {
    const inputField = <FormArray>this.additionFormGroup.get('translation')
    inputField.push(this.createTranslationFormGroup());

  }


  onSubmit() {

    if(this.additionFormGroup.invalid){
      this.additionFormGroup.markAllAsTouched();
      return false;
    }
    this.word.word = this.additionFormGroup.get('addWord').value;
    let arr = [];
    <FormArray> this.additionFormGroup.get('translation')['controls'].forEach(v=>arr.push(v.value['translation']));
    this.word.translations = arr;
    this.additionService.addNewWord(this.language.value,this.word).subscribe(data=>this.response = data);
    this.additionFormGroup.markAsUntouched();
    this.newWord.setValue('');
    for(let i = 1; i<this.getFields().length; i++){
      this.getFields().removeAt(i);
    }


  }

  wordValid() {
    return this.additionFormGroup.get('addWord').touched&&this.additionFormGroup.get('addWord').invalid;
  }

  txValid() {
    return this.additionFormGroup.get('translation').touched&&this.additionFormGroup.get('translation').invalid;
  }

  get newWord () {
    return this.additionFormGroup.get('addWord');
  }

  get language () {
    return this.additionFormGroup.get('lang');
  }

  get translations () {
    return this.additionFormGroup.get('translation');
  }

  setTimer () {
    this.timer = true;
    setTimeout(()=>{
      this.timer = false;
    },3000)
  }

  removeField() {
    if(this.getFields().length>1){
      this.getFields().removeAt(this.getFields().length-1)
    }
  }
}
