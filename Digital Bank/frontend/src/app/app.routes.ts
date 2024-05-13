import { Routes } from '@angular/router';
import {AccountsComponent} from "./components/accounts/accounts.component";
import {CustomersComponent} from "./components/customers/customers.component";
import {NewCustomerComponent} from "./components/new-customer/new-customer.component";

export const routes: Routes = [
  { path :"customers", component : CustomersComponent},
  { path :"accounts", component : AccountsComponent},
  { path :"new-customer", component : NewCustomerComponent},
  //{ path :"customer-accounts/:id", component : CustomerAccountsComponent},
];
