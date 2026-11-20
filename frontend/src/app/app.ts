import { Component } from '@angular/core';
import { LoginDialog } from './login-dialog/login-dialog';

@Component({
  selector: 'app-root',
  imports: [LoginDialog],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {}