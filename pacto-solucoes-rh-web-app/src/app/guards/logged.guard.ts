import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { catchError, Observable, of, tap } from "rxjs";
import { SessionService } from "../services/session.service";

@Injectable({ providedIn: 'root' })
export class LoggedGuard implements CanActivate {
    constructor(
        private _sessionService: SessionService,
        private router: Router
    ) { }

    /**
     * Se der erro para consultar info da sessao do usuario redireciona para LOGIN caso ja nao esteja em login
     * Mas se tentar acessar login com os dados de sessao verificaveis, apenas retoran o usuario para a tela inicial.
     * @param route 
     * @param _state 
     * @returns 
     */
    canActivate(route: ActivatedRouteSnapshot, _state: RouterStateSnapshot): Observable<boolean> {
        return this._sessionService.info().pipe(tap({
            next: () => {
                this.router.navigate(['']);
                return false;
            }
        }), catchError(error => {
            const login = route.url.find(url => url.path == 'login');
            if (login) {
                return of(true)
            } else {
                this.router.navigate(['/login']);
                return of(false);
            }
        }))
    }
}