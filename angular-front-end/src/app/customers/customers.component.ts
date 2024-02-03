import {Component, OnInit} from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
import {NgForOf, NgIf} from "@angular/common";
import {KeycloakAngularModule, KeycloakBearerInterceptor} from "keycloak-angular";

@Component({
  selector: 'app-customers',
  standalone: true,
  imports: [
    HttpClientModule,
    NgForOf,
    NgIf,
    KeycloakAngularModule
  ],
  providers:[
    {
      provide: HTTP_INTERCEPTORS,
      useClass: KeycloakBearerInterceptor,
      multi: true
    },
  ],
  templateUrl: './customers.component.html',
  styleUrl: './customers.component.css'
})
export class CustomersComponent implements OnInit{
  customers : any;
  constructor(private http: HttpClient) {
  }
  ngOnInit(): void {
    this.http.get("http://localhost:8888/CUSTOMER-SERVICE/customers")
      .subscribe({
        next : data => {
          this.customers = data;
        },
        error : err => console.log(err)
      })
  }

}
