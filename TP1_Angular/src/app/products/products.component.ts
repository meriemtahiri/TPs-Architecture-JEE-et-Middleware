import {Component, OnInit} from '@angular/core';
import {CommonModule} from "@angular/common";
import {ProductService} from "../services/product.service";
import {ProductModel} from "../model/product.model";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit{
  keyword: string=""
  constructor(private productService: ProductService) {
  }

  products : Array<ProductModel> = []

  ngOnInit(): void {
    this.productService.getProducts()
      .subscribe({
        next : data => {this.products = data; console.log(data)},
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



  searchProduct() {
    this.productService.search(this.keyword)
      .subscribe({
        next : data => console.log(data)
      })
  }
}
