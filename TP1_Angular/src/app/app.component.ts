import { Component } from '@angular/core';
import {RouterLink, RouterOutlet} from '@angular/router';
import {CommonModule, NgForOf} from "@angular/common";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    RouterLink,
    NgForOf,
    CommonModule,
    HttpClientModule,

  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  actions : Array<any> = [
    {title : "home", route : "/home", icon : "house"},
    {title : "products", route : "/products", icon : "search"},
    {title : "newProducts", route : "/newProduct", icon : "plus"},
  ]
  currentAction : any
  setCurrentAction(action: any) {
    this.currentAction = action
  }
}
