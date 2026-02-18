import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Apizitak } from '../apizitak';
import { FormsModule } from '@angular/forms';
import { IonHeader, IonToolbar, IonTitle, IonContent, IonButton, IonList, IonItem, IonInput } from '@ionic/angular/standalone';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
  imports: [IonHeader, IonToolbar, IonTitle, IonContent, IonButton, IonList, IonItem, IonInput, FormsModule],
})
export class HomePage {

  erabiltzailea: string = '';
  password: string = '';

  constructor(
    private api: Apizitak,
    private router: Router
  ) {}

  login() {
  this.api.login(this.erabiltzailea, this.password).subscribe({
    next: (response) => {

      // 👇 asegurarse de guardar el ID correcto
      localStorage.setItem('clienteId', response.id.toString());

      this.router.navigate(['/zitak']);
    },
    error: () => {
      alert('Login incorrecto');
    }
  });
}
}
