import { Injectable } from "@angular/core";
import { SessionDataModel } from "../models/session-data.model";

@Injectable({providedIn: 'root'})
export class SessionDataService {
    private _sessionData?: SessionDataModel;

    public setSessionData(sessionData: any) {
        this._sessionData = sessionData;
    }

    public temPapel(papelNome: string): boolean {
        return !!this._sessionData!.papeis?.find(papel => papel.nome == papelNome);
    }
}