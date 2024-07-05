import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { UsuarioRequestModel } from "../../models/usuario-request.model";
import { AuthService } from "../../services/auth.service";

@Component({
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    public formulario: FormGroup = this.formBuilder.group(new UsuarioRequestModel());

    constructor(
        private formBuilder: FormBuilder,
        private authService: AuthService,
        private router: Router,
    ) { }

    ngOnInit(): void {
        this.formulario.controls['nome']?.setValidators([Validators.required]);
        this.formulario.controls['senha']?.setValidators([Validators.required]);
    }

    public entrar() {
        const value = this.formulario.value;
        this.authService.login(value.nome, value.senha).subscribe({
            next: (resp) => {
                this.router.navigate(['/']);
            },
            error: (error) => {
            }
        });
    }

}