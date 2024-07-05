import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { SessionDataService } from "../../../services/session-data.service";
import { VagaService } from "../../../services/vaga.service";
import { PagerModel } from "../../../models/pager.model";
import { forkJoin, map, Observable } from "rxjs";
import { PaginatorState } from "primeng/paginator";
import { AuthService } from "../../../services/auth.service";

@Component({
    templateUrl: './pesquisar-vaga.component.html',
    styleUrls: ['./pesquisar-vaga.component.scss']
})
export class PesquisarVagaComponent implements OnInit {

    filtro: any = '';
    pagerModel: PagerModel = new PagerModel();
    vagas: any = [];

    constructor(
        private _sessionDataService: SessionDataService,
        private _vagaService: VagaService,
        private _authService: AuthService,
        private _router: Router
    ) { }

    ngOnInit(): void {
        this.consultaInicial().subscribe({
            next: () => {
            },
            error: (error) => console.error(error)
        })
    }

    public navegarAdicionarNovaVaga() {
        this._router.navigate(['', 'nova-vaga']);
    }

    public permissaoAdmin(): boolean {
        return this._sessionDataService.temPapel('ADMIN');
    }

    public pesquisar(event: KeyboardEvent) {
        if (event.key === 'Enter' || event.key === 'Backspace' && this.filtro === '') {
            this._vagaService.consultar(1, 10, this.filtro).subscribe({
                next: (resp) => {
                    this.montarVagasPaginadas(resp);
                },
                error: (err) => console.error(err)
            });
        }
    }

    public onPageChange(event: PaginatorState) {
        this._vagaService.consultar(event.page! + 1, event.rows!, this.filtro).subscribe({
            next: (resp) => this.montarVagasPaginadas(resp)
        })
    }

    public sair() {
        this._authService.logout().subscribe({
            next: () => {
                this._router.navigate(['login']);
            }
        });
    }

    private consultaInicial(): Observable<any> {
        return forkJoin([
            this._vagaService.consultar(1, 10, '').pipe(map(resp => {
                this.montarVagasPaginadas(resp);
            }))
        ]);
    }

    private montarVagasPaginadas(resp: any) {
        this.vagas = resp.content;
        this.pagerModel.total = resp.totalElements;
        this.pagerModel.first = resp?.pageable?.offset;
        this.pagerModel.rows = resp?.pageable?.pageSize;
    }
}