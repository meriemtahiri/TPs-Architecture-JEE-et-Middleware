import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ProductModel} from "../model/product.model";
@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }
  public getProducts(keyword: string,page: number=1,size: number=1){
    return this.http.get<Array<ProductModel>>(`http://localhost:8080/products?name_like=${keyword}&_page=${page}&_limit=${size}`,{observe: 'response'})
  }
  public patchCheckedProduct(product: ProductModel){
    return this.http.patch<ProductModel>(`http://localhost:8080/products/${product.id}`,{checked:!product.checked})
  }

  public deleteProduct(product: ProductModel):Observable<ProductModel> {
    return this.http.delete<ProductModel>(`http://localhost:8080/products/${product.id}`)
  }

  addProduct(product: ProductModel):Observable<ProductModel> {
    return this.http.post<ProductModel>(`http://localhost:8080/products`,product)
  }


  updateProduct(product: ProductModel) {
    return this.http.patch<ProductModel>(`http://localhost:8080/products/${product.id}`,product)
  }

  getProductById(id: number) {
    return this.http.get<ProductModel>(`http://localhost:8080/products/${id}`)
  }
}
