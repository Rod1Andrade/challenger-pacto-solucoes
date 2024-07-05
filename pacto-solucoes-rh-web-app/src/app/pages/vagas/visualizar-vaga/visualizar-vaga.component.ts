import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Route, Router } from "@angular/router";
import { VagaService } from "../../../services/vaga.service";
import { SessionDataService } from "../../../services/session-data.service";
import { ConfirmationService, MessageService } from "primeng/api";
import { forkJoin, map } from "rxjs";

@Component({
    templateUrl: './visualizar-vaga.component.html',
    styleUrls: ['./visualizar-vaga.component.scss']
})
export class VisualizarVagaComponent implements OnInit {

    jaInscrito: boolean = false;
    vagaId: any = this._route.snapshot.params['id'];
    vaga?: any;

    constructor(
        private _confirmationService: ConfirmationService,
        private _sessionDataService: SessionDataService,
        private _messageService: MessageService,
        private _vagaService: VagaService,
        private _route: ActivatedRoute,
        private _router: Router
    ) { }

    ngOnInit(): void {
        forkJoin([
            this._vagaService.obter(this.vagaId).pipe(map(resp => {
                this.vaga = resp;
            })),
            this._vagaService.verificaInscricao(this.vagaId)
            .pipe(map(resp => {
                this.jaInscrito = resp.jaInscrito;
            }))
        ]).subscribe({
            next: () => {},
            error: () => {
                this._messageService.add({severity: 'error', summary: 'Erro inesperado', detail: 'Algo deu errado'})
            }
        });
    }

    public inscrever(event: Event) {
        this._confirmationService.confirm({
            target: event.target as EventTarget,
            header: 'Inscrição',
            message: 'Deseja realmente inscrever-se nesta vaga?',
            rejectButtonStyleClass: "p-button-text",
            acceptLabel: 'Sim',
            rejectLabel: 'Não',
            accept: () => {
                this._vagaService.inscrever(this.vagaId).subscribe({
                    next: () => {
                        this._messageService.add({
                            severity: 'success',
                            summary: 'Sucesso',
                            detail: 'Inscrição realizada com sucesso!'
                        })
                        this.jaInscrito = true;
                    }
                })
            }
        });
    }

    public usuarioComum(): boolean {
        return this._sessionDataService.temPapel('USUARIO_COMUM');
    }

    public voltar() {
        this._router.navigate(['']);
    }

    public editar() {
        this._router.navigate(['editar', this.vaga?.id])
    }
}