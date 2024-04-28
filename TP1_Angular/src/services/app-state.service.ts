import { Injectable } from '@angular/core';
import {ProductModel} from "../model/product.model";

@Injectable({
  providedIn: 'root'
})
export class AppStateService {
  public productState = {
    products : [] as ProductModel[],
    keyword: "",
    currentPage :1,
    pageSize : 3,
    totalPages : 0,
    totalProducts : 0,
    checked: 0

  }
  constructor() { }
}
