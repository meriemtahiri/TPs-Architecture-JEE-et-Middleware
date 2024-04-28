import { Routes } from '@angular/router';
import {HomeComponent} from "../components/home/home.component";
import {ProductsComponent} from "../components/products/products.component";
import {NewProductsComponent} from "../components/new-products/new-products.component";
import {UpdateProductComponent} from "../components/update-product/update-product.component";

export const routes: Routes = [
  {path : "home" , component : HomeComponent},
  {path : "products" , component : ProductsComponent},
  {path : "newProduct" , component : NewProductsComponent},
  {path : "updateProduct/:id" , component : UpdateProductComponent},
];
