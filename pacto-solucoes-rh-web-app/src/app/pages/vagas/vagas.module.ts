import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { ReactiveFormsModule } from "@angular/forms";
import { ButtonModule } from "primeng/button";
import { CardModule } from "primeng/card";
import { ChipModule } from "primeng/chip";
import { ConfirmDialogModule } from "primeng/confirmdialog";
import { EditorModule } from "primeng/editor";
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { InputTextModule } from 'primeng/inputtext';
import { PaginatorModule } from "primeng/paginator";
import { RippleModule } from "primeng/ripple";
import { TooltipModule } from "primeng/tooltip";
import { FormVagaComponent } from "./form-vaga/form-vaga.component";
import { PesquisarVagaComponent } from "./pesquisar-vaga/pesquisar-vaga.component";
import { VagasRougintModule } from "./vagas-routing.module";
import { VisualizarVagaComponent } from "./visualizar-vaga/visualizar-vaga.component";
import { ConfirmationService } from "primeng/api";

@NgModule({
    declarations: [PesquisarVagaComponent, FormVagaComponent, VisualizarVagaComponent],
    imports: [
        ChipModule, 
        CardModule,
        RippleModule,
        EditorModule,
        ButtonModule,
        CommonModule,
        TooltipModule,
        PaginatorModule,
        InputIconModule,
        InputTextModule,
        IconFieldModule,
        VagasRougintModule,
        ReactiveFormsModule,
        ConfirmDialogModule, 
    ],
    providers: [ConfirmationService]
})
export class VagasModule { }