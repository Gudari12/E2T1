import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class Apizitak {

  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  login(nombre: string, phone: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/client`, {
      nombre: nombre,
      phone: phone
    });
  }

  getAppointments(clienteId: string) {
  return this.http.get(`${this.apiUrl}/appointment`);
}

  getZerbitzuak() {
  return this.http.get(`${this.apiUrl}/zerbitzua`);
}

}
