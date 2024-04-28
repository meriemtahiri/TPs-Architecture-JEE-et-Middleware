import { Injectable } from '@angular/core';
import {ProductModel} from "../model/product.model";

@Injectable({
  providedIn: 'root'
})
export class AppStateService {
  public productState = {
    products : [],
    keyword: "",
    currentPage :1,
    pageSize : 3,
    totalPages : 0,
  }
  constructor() { }
}
