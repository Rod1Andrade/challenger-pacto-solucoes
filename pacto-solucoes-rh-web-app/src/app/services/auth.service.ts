import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, take } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private _api = 'http://localhost:8080/auth';

    constructor(
        private http: HttpClient,
    ) { }

    public login(nome: string, senha: string): Observable<any> {
        return this.http.post(`${this._api}/login`, { nome, senha }, { withCredentials: true }).pipe(take(1));
    }

    public logout(): Observable<any> {
        return this.http.post(`${this._api}/logout`, {}, { withCredentials: true }).pipe(take(1));
    }

}