import {Component, OnInit} from '@angular/core';
import {CommonModule} from "@angular/common";
import {ProductService} from "../../services/product.service";
import {ProductModel} from "../../model/product.model";
import {FormsModule} from "@angular/forms";
import { Router } from '@angular/router';
import {AppStateService} from "../../services/app-state.service";

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit{
  constructor(private productService: ProductService, private router: Router, public appState : AppStateService) {
  }


  ngOnInit(): void {
    this.getProducts()
  }
  getProducts(){
    this.productService.getProducts(this.appState.productState.keyword,this.appState.productState.currentPage,this.appState.productState.pageSize)
      .subscribe({
          error: err => console.log(err),
          next: res => {
            this.appState.productState.products = res.body as ProductModel[]
            let NBP: number = parseInt(res.headers.get('x-total-count')!);
            this.appState.productState.totalProducts=NBP;
            this.appState.productState.totalPages = Math.floor(NBP / this.appState.productState.pageSize)
            if (NBP % this.appState.productState.pageSize != 0) this.appState.productState.totalPages++
          }
        }
      )
  }
  handleCheckProduct(product: ProductModel) {
    this.productService.patchCheckedProduct(product)
      .subscribe({
        next : () => {
          product.checked=!product.checked
        }
        ,
        error : err => console.log(err)
      })
  }

  deleteProduct(product: ProductModel) {
    if(confirm("are you sur ?"))
    this.productService.deleteProduct(product)
      .subscribe({
        next : () => this.appState.productState.products=this.appState.productState.products.filter(p=>p.id!=product.id)
      })
  }

  handlePage(page: number) {
    this.appState.productState.currentPage=page
    this.getProducts()
  }

  updateProduct(product: ProductModel) {
    this.router.navigateByUrl(`updateProduct/${product.id}`).then(r => {console.log(r)})
  }
}
