import { Routes } from '@angular/router';
import {CustomersComponent} from "./customers/customers.component";
import {AccountsComponent} from "./accounts/accounts.component";
import {AuthGuard} from "./guards/auth-guard.guard";

export const routes: Routes = [
  {path : "customers", component : CustomersComponent, canActivate:[AuthGuard], data:{roles:[]}},
  {path : "accounts", component : AccountsComponent},
];
