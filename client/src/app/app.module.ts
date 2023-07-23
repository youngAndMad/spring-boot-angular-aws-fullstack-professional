import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { AuthComponent } from './components/auth/auth.component';
import {ReactiveFormsModule} from "@angular/forms";
import { VerificationComponent } from './components/verification/verification.component';
import { ProfileComponent } from './components/profile/profile.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './components/home/home.component';
import { SidenavComponent } from './components/sidenav/sidenav.component';
import { FilesComponent } from './components/files/files.component';
import {AuthInterceptor} from "./interceptor/auth.interceptor";

@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    VerificationComponent,
    ProfileComponent,
    NotFoundComponent,
    HomeComponent,    SidenavComponent, FilesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,

  ],
  providers: [
    {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true,
  },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
