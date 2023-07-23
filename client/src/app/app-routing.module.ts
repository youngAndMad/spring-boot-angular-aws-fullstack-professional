import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthComponent} from "./components/auth/auth.component";
import {VerificationComponent} from "./components/verification/verification.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {NotFoundComponent} from "./components/not-found/not-found.component";

const routes: Routes = [
  {path: 'login', component: AuthComponent},
  {path: 'verification', component: VerificationComponent},
  {path: 'profile', component: ProfileComponent},
  {path: '**', component: NotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
