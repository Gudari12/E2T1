import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Apizitak } from '../apizitak';
import { FormsModule } from '@angular/forms';
import { 
  IonHeader, IonToolbar, IonTitle, IonContent, 
  IonButton, IonList, IonItem, IonInput, 
  IonButtons, IonIcon 
} from '@ionic/angular/standalone';
import { Theme } from '../services/theme';

import { addIcons } from 'ionicons'; 
import { 
  moon, sunny, moonOutline, sunnyOutline, 
  calendarOutline, timeOutline, listOutline 
} from 'ionicons/icons';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
  standalone: true, 
  imports: [
    IonHeader, IonToolbar, IonTitle, IonContent, 
    IonButton, IonList, IonItem, IonInput, 
    FormsModule, IonButtons, IonIcon
  ],
})
export class HomePage {

  erabiltzailea: string = '';
  password: string = '';

  constructor(
    private api: Apizitak,
    private router: Router,
    public theme: Theme
  ) {
    addIcons({ 
      moon, 
      sunny, 
      'moon-outline': moonOutline, 
      'sunny-outline': sunnyOutline,
      'calendar-outline': calendarOutline,
      'time-outline': timeOutline,
      'list-outline': listOutline 
    });
  }

  toggleDarkMode() {
    this.theme.toggleTheme();
  }

  login() {
    this.api.login(this.erabiltzailea, this.password).subscribe({
      next: (response) => {
        localStorage.setItem('clienteId', response.id.toString());
        this.router.navigate(['/zitak']);
      },
      error: (err) => {
        console.error('Error en login:', err);
        alert('Erabiltzaile edo pasahitz okerra');
      }
    });
  }
}