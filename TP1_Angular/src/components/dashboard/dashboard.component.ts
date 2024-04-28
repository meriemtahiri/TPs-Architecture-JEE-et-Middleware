import { Component } from '@angular/core';
import {NgForOf} from "@angular/common";
import {RouterLink} from "@angular/router";
import {AppStateService} from "../../services/app-state.service";

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    NgForOf,
    RouterLink
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

  constructor(protected state : AppStateService) {
  }

  checkedProducts() {
    return this.state.productState.checked=this.state.productState.products.filter(product => product.checked).length
  }
}
