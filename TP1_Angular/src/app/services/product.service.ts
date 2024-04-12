import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ProductModel} from "../model/product.model";
@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }
  public getProducts(): Observable<Array<ProductModel>>{
    return this.http.get<Array<ProductModel>>("http://localhost:8080/products")
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

  search(keyword: string):Observable<Array<ProductModel>> {
    return this.http.get<Array<ProductModel>>(`http://localhost:8080/products?name_like=${keyword}`)
  }
}
