import { Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {ProductsComponent} from "./products/products.component";
import {NewProductsComponent} from "./new-products/new-products.component";

export const routes: Routes = [
  {path : "home" , component : HomeComponent},
  {path : "products" , component : ProductsComponent},
  {path : "newProduct" , component : NewProductsComponent},
];
