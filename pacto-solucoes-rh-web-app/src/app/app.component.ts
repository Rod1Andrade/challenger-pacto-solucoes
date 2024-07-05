import { CommonModule } from '@angular/common';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MessageService, PrimeNGConfig } from 'primeng/api';
import { AuthInterceptor } from './interceptors/auth.interceptor';
import { LoginModule } from './pages/login/login.module';
import { VagasModule } from './pages/vagas/vagas.module';
import { ToastModule } from 'primeng/toast';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    ToastModule,
    LoginModule,
    RouterOutlet,
    CommonModule,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
  providers: [
    MessageService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ]
})
export class AppComponent {
  title = 'pacto-solucoes-rh-web';

  constructor(private primengConfig: PrimeNGConfig) { }

  ngOnInit() {
    this.primengConfig.ripple = true;
  }
}
