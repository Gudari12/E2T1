import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonContent, IonHeader, IonTitle, IonToolbar, IonList, IonItem, IonLabel } from '@ionic/angular/standalone';
import { Apizitak } from '../apizitak';

@Component({
  selector: 'app-zerbitzuak',
  templateUrl: './zerbitzuak.page.html',
  styleUrls: ['./zerbitzuak.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule, IonList, IonItem, IonLabel]
})
export class ZerbitzuakPage implements OnInit {

  services: any[] = []; // Aquí guardaremos los servicios

  constructor(private api: Apizitak) { }

  ngOnInit() {
    this.api.getZerbitzuak().subscribe({
      next: (data: any) => {
        this.services = data; // Guardamos los servicios del API
      },
      error: (err) => {
        console.error('Error al obtener servicios', err);
      }
    });
  }

}
