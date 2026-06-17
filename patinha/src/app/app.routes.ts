import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { PetsComponent } from './pages/pets/pets.component';
import { PetDetailComponent } from './pages/pet-detail/pet-detail.component';
import { AdoptionFormComponent } from './pages/adoption-form/adoption-form.component';
import { NoticiasComponent } from './pages/noticias/noticias.component';
import { ContatoComponent } from './pages/contato/contato.component';
import { LoginComponent } from './pages/login/login.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'pets', component: PetsComponent },
  { path: 'pet/:id', component: PetDetailComponent },
  { path: 'adotar', component: AdoptionFormComponent },
  { path: 'noticias', component: NoticiasComponent },
  { path: 'contato', component: ContatoComponent },
  { path: 'login', component: LoginComponent },
  { path: '**', redirectTo: '' }
];
