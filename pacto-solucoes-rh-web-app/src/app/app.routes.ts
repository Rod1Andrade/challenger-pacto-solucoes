import { Routes } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';
import { LoggedGuard } from './guards/logged.guard';
import { LoginComponent } from './pages/login/login.component';

export const routes: Routes = [
    {
        path: '',
        loadChildren: () => import('./pages/vagas/vagas.module').then(m => m.VagasModule),
        canActivate: [AuthGuard]
    },
    {
        path: 'login',
        component: LoginComponent,
        canActivate: [LoggedGuard]
    }
];
