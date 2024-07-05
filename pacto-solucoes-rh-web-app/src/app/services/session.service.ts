import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, take } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class SessionService {
    private _api = 'http://localhost:8080/api/sessions';

    constructor(
        private http: HttpClient,
    ) { }

    public info(): Observable<any> {
        return this.http.get<any>(`${this._api}/info`, { withCredentials: true, reportProgress: true }).pipe(take(1));
    }

}