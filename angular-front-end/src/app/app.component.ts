import {Component, OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterLink, RouterOutlet} from '@angular/router';
import {KeycloakAngularModule, KeycloakService} from "keycloak-angular";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink, KeycloakAngularModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'angular-front-end';
  authenticated = false;

  constructor(private readonly keycloak : KeycloakService) {
      this.authenticated = this.keycloak.isLoggedIn().valueOf();
  }

  ngOnInit(): void {
  }

  async login(){
    await this.keycloak.login({
      redirectUri: window.location.origin
    })
  }
  logout(){
    this.keycloak.logout(window.location.origin)
  }
}
