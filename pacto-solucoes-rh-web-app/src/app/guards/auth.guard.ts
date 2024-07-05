import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { Observable, tap } from "rxjs";
import { SessionDataService } from "../services/session-data.service";
import { SessionService } from "../services/session.service";

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
    constructor(
        private _sessionDataService: SessionDataService,
        private _sessionService: SessionService,
        private router: Router
    ) { }

    /**
     * Redireciona para login se nao estiver conseguindo verificar dados da sessao.
     * @param route 
     * @param _state 
     * @returns 
     */
    canActivate(route: ActivatedRouteSnapshot, _state: RouterStateSnapshot): Observable<boolean> {
        return this._sessionService.info().pipe(tap({
            next: (sessionData) => {
                this._sessionDataService.setSessionData(sessionData);
                return true;
            },
            error: (err) => {
                this.router.navigate(['/login']);
                return false;
            }
        }))
    }
}