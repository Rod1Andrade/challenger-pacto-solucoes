import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, take } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class VagaService {
   
    private _api = 'http://localhost:8080/api/vaga';

    constructor(
        private http: HttpClient,
    ) {

    }

    public anunciar(vagaRequest: any): Observable<any> {
        return this.http.post(`${this._api}`, vagaRequest, { withCredentials: true }).pipe(take(1));
    }

    public editar(vagaRequest: any, vagaId: any): Observable<any> {
        return this.http.put(`${this._api}/${vagaId}`, vagaRequest, { withCredentials: true }).pipe(take(1));
    }

    public consultar(pagina: number, quantidade: number, filtro: string): Observable<any> {
        return this.http.get(`${this._api}`,
            {
                params: {
                    pagina: pagina,
                    quantidade: quantidade,
                    filtro: filtro
                },
                withCredentials: true
            })
            .pipe(take(1));
    }

    public obter(id: number): Observable<any> {
        return this.http.get(`${this._api}/obter/${id}`, { withCredentials: true }).pipe(take(1));
    }

    public inscrever(id: number): Observable<any> {
        return this.http.post(`${this._api}/inscrever-se/${id}`, {}, { withCredentials: true }).pipe(take(1));
    }

    public verificaInscricao(vagaId: any): Observable<any> {
        return this.http.get(`${this._api}/verifica-inscricao/${vagaId}`, {withCredentials: true}).pipe(take(1));
    }

}