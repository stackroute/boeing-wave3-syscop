import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { AppRegistrationComponent } from './components/app-registration/app-registration.component';
import { SignupComponent } from './components/signup/signup.component';
import { LandingComponent } from './components/landing/landing.component';
import { NotificationsComponent } from './components/notifications/notifications.component';
import { DataComponent } from './components/data/data.component';
import { JavaGraphsComponent } from './components/java-graphs/java-graphs.component';
import { DockerComponent } from './components/docker/docker.component';

const routes: Routes = [
  {path: '', component: LandingComponent},
  {path: 'login', component: LoginComponent, },
  {path: 'signup', component: SignupComponent},
  { path: 'home',
    component: HomeComponent,
    children: [
      {path: '', component: AppRegistrationComponent },
      {path: 'dashboard', component: DockerComponent },
      {path: 'appRegistration', component: AppRegistrationComponent },
      {path: 'notifications', component: NotificationsComponent },
      {path: 'data', component: DataComponent },
      {path: 'java', component: JavaGraphsComponent },
    ]
  }, ];


@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true }), ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
