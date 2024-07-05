import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { Observable, of } from "rxjs";
import { SessionDataService } from "../services/session-data.service";
import { Injectable } from "@angular/core";

@Injectable({ providedIn: 'root' })
export class AdminGuard implements CanActivate {

    constructor(
        private _sessionDataService: SessionDataService,
        private _router: Router
    ) { }

    canActivate(route: ActivatedRouteSnapshot, _state: RouterStateSnapshot): Observable<boolean> {
        if (this._sessionDataService.temPapel('ADMIN')) {
            return of(true);
        } else {
            this._router.navigate(['']);
            return of(false);
        }
    }
}