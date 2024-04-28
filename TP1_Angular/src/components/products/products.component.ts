import {Component, OnInit} from '@angular/core';
import {CommonModule} from "@angular/common";
import {ProductService} from "../services/product.service";
import {ProductModel} from "../model/product.model";
import {FormsModule} from "@angular/forms";
import { Router } from '@angular/router';
import {AppStateService} from "../services/app-state.service";

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

  products : Array<ProductModel> = this.appState.productState.products
  keyword: string=this.appState.productState.keyword
  currentPage : number=this.appState.productState.currentPage
  pageSize : number=this.appState.productState.pageSize
  totalPages : number=this.appState.productState.totalPages

  ngOnInit(): void {
    this.getProducts()
  }
  getProducts(){
    this.productService.getProducts(this.keyword,this.currentPage,this.pageSize)
      .subscribe({
          next : res =>{
            this.products = res.body as ProductModel[]
            let NBP : number =  parseInt(res.headers.get('x-total-count')!);
            this.totalPages = Math.floor(NBP / this.pageSize)
            if(NBP % this.pageSize !=0) this.totalPages++
          } ,
          error : err => console.log(err)
        }
      )
  }
  handleCheckProduct(product: ProductModel) {
    this.productService.patchCheckedProduct(product)
      .subscribe({
        next : () => product.checked=!product.checked
        ,
        error : err => console.log(err)
      })
  }

  deleteProduct(product: ProductModel) {
    if(confirm("are you sur ?"))
    this.productService.deleteProduct(product)
      .subscribe({
        next : () => this.products=this.products.filter(p=>p.id!=product.id)
      })
  }

  handlePage(page: number) {
    this.currentPage=page
    this.getProducts()
  }

  updateProduct(product: ProductModel) {
    this.router.navigateByUrl(`updateProduct/${product.id}`)
  }
}
