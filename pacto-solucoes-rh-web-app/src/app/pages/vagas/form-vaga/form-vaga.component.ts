import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { VagaService } from "../../../services/vaga.service";
import { tap } from "rxjs";
import { ConfirmationService, MessageService } from "primeng/api";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
    templateUrl: './form-vaga.component.html',
    styleUrls: ['./form-vaga.component.scss']
})
export class FormVagaComponent implements OnInit {

    vagaId: any = this._route.snapshot.params['idVaga'];

    formulario: FormGroup = this._formBuilder.group({
        titulo: ['', Validators.required],
        subtitulo: ['', Validators.required],
        descricao: ['', Validators.required],
        texto: ['', Validators.required],
    });

    constructor(
        private _messageService: MessageService,
        private _formBuilder: FormBuilder,
        private _vagaService: VagaService,
        private _route: ActivatedRoute,
        private _router: Router,
    ) {

    }

    ngOnInit(): void {
        if (this.vagaId) {
            this._vagaService.obter(this.vagaId).subscribe({
                next: (resp) => {
                    this.formulario.patchValue(resp);
                }
            })
        }
    }

    public submit() {
        if (this.vagaId) {
            this.editar();
        } else {
            this.anunciar();
        }
    }

    public editar(): void {
        if (!this.validarForm()) return;

        const vagaRequest: any = this.formulario.value;
        this._vagaService.editar(vagaRequest, this.vagaId).subscribe({
            next: (resp) => {
                this._messageService.add({
                    severity: 'success',
                    summary: 'Sucesso',
                    detail: 'Vaga anunciada com sucesso!'
                })

                this._router.navigate(['visualizar', resp]);
            },
            error: (error) => {
                this._messageService.add({
                    severity: 'error',
                    summary: 'Erro',
                    detail: 'Algo inesperado aconteceu!'
                })
            }
        });
    }

    public anunciar(): void {
        if (!this.validarForm()) return;

        const vagaRequest: any = this.formulario.value;
        this._vagaService.anunciar(vagaRequest).subscribe({
            next: (resp) => {
                this._messageService.add({
                    severity: 'success',
                    summary: 'Sucesso',
                    detail: 'Vaga anunciada com sucesso!'
                })

                this._router.navigate(['visualizar', resp]);
            },
            error: (error) => {
                this._messageService.add({
                    severity: 'error',
                    summary: 'Erro',
                    detail: 'Algo inesperado aconteceu!'
                })
            }
        });
    }

    public voltar() {
        this._router.navigate(['']);
    }

    private validarForm(): boolean {
        if (this.formulario.controls['texto'].value == '') {
            this._messageService.add({ severity: 'warn', summary: 'Texto não pode ser vázio', detail: 'Adicione os dados complementares da vaga.' })
            return false
        }
        if (this.formulario.invalid) {
            this.formulario.markAllAsTouched();
            return false;
        }

        return true;
    }

}