import { Component, OnInit } from '@angular/core';
import { Apizitak } from '../apizitak';
import { Router } from '@angular/router';
import { IonHeader, IonToolbar, IonTitle, IonContent, IonCard, IonCardContent, IonText, IonList, IonItem, IonLabel, IonNote, IonButton, IonIcon, IonButtons, IonToggle } from "@ionic/angular/standalone";
import { CommonModule } from '@angular/common';
import { Theme } from '../services/theme';
import { addIcons } from 'ionicons'; 
import { moon, sunny, moonOutline, sunnyOutline, calendarOutline, timeOutline, listOutline } from 'ionicons/icons';

@Component({
  selector: 'app-zitak',
  templateUrl: './zitak.page.html',
  styleUrls: ['./zitak.page.scss'],
  standalone: true,
  imports: [
    CommonModule,
    IonHeader,
    IonToolbar,
    IonTitle,
    IonContent,
    IonCard,
    IonCardContent,
    IonText,
    IonList,
    IonItem,
    IonLabel,
    IonNote,
    IonButton,
    IonIcon,
    IonButtons,
    IonToggle
],
})
export class ZitakPage implements OnInit {

  citas: any;
  siguienteCita: any;

  constructor(private api: Apizitak, 
    private router: Router,
  public theme: Theme) {}

ngOnInit() {
  this.cargarCitas();
}
  toggleDarkMode() {
    this.theme.toggleTheme();
  }

cargarCitas() {

  const clienteId = localStorage.getItem('clienteId');

  if (!clienteId) {
    console.error('No hay clienteId guardado');
    return;
  }

  this.api.getAppointments(clienteId).subscribe({
    next: (data) => {

      this.citas = data;

      if (this.citas.length > 0) {
        this.siguienteCita = this.citas[0];
      }

    },
    error: (err) => {
      console.error('Error cargando citas', err);
    }
  });
}

z(){
this.router.navigate(['/zerbitzuak']);
}
}
