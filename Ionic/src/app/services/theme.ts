import { Injectable } from '@angular/core';
import { addIcons } from 'ionicons'; 
import { moon, sunny, moonOutline, sunnyOutline, calendarOutline, timeOutline, listOutline } from 'ionicons/icons';

@Injectable({ providedIn: 'root' })
export class Theme {
  public isDarkMode: boolean = false;

  constructor() {
    const pref = localStorage.getItem('darkMode');
    this.isDarkMode = pref ? JSON.parse(pref) : false;
    this.applyTheme();
  }

  toggleTheme() {
    this.isDarkMode = !this.isDarkMode;
    this.applyTheme();
    localStorage.setItem('darkMode', JSON.stringify(this.isDarkMode));
  }

  private applyTheme() {
    document.body.classList.toggle('dark', this.isDarkMode);
  }
}