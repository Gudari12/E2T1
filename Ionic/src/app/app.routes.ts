import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'home',
    loadComponent: () => import('./home/home.page').then((m) => m.HomePage),
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },
  {
    path: 'zitak',
    loadComponent: () => import('./zitak/zitak.page').then( m => m.ZitakPage)
  },
  {
    path: 'zerbitzuak',
    loadComponent: () => import('./zerbitzuak/zerbitzuak.page').then( m => m.ZerbitzuakPage)
  },
];
