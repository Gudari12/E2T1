import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonContent, IonHeader, IonTitle, IonToolbar, IonList, IonItem, IonLabel, IonIcon, IonButtons, IonButton } from '@ionic/angular/standalone';
import { Apizitak } from '../apizitak';
import { Theme } from '../services/theme';
import { addIcons } from 'ionicons'; 
import { moon, sunny, moonOutline, sunnyOutline, calendarOutline, timeOutline, listOutline } from 'ionicons/icons';

@Component({
  selector: 'app-zerbitzuak',
  templateUrl: './zerbitzuak.page.html',
  styleUrls: ['./zerbitzuak.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule, IonList, IonItem, IonLabel, IonIcon, IonButtons, IonButton]
})
export class ZerbitzuakPage implements OnInit {

  services: any[] = [];

  constructor(private api: Apizitak,
    public theme: Theme
  ) { }

  toggleDarkMode() {
    this.theme.toggleTheme();
  }

  ngOnInit() {
    this.api.getZerbitzuak().subscribe({
      next: (data: any) => {
        this.services = data;
      },
      error: (err) => {
        console.error('Error al obtener servicios', err);
      }
    });
  }

}
