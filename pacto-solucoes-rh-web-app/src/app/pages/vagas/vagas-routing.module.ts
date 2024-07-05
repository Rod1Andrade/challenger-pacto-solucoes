import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { FormVagaComponent } from "./form-vaga/form-vaga.component";
import { PesquisarVagaComponent } from "./pesquisar-vaga/pesquisar-vaga.component";
import { AdminGuard } from "../../guards/admin.guard";
import { VisualizarVagaComponent } from "./visualizar-vaga/visualizar-vaga.component";
import { AuthGuard } from "../../guards/auth.guard";

const routes: Routes = [
    {
        path: '',
        component: PesquisarVagaComponent,
        canActivate: [AuthGuard]
    },
    {
        path: 'nova-vaga',
        component: FormVagaComponent,
        canActivate: [AdminGuard]
    },
    {
        path: 'editar/:idVaga',
        component: FormVagaComponent,
        canActivate: [AdminGuard]
    },
    {
        path: 'visualizar/:id',
        component: VisualizarVagaComponent,
        canActivate: [AuthGuard]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class VagasRougintModule { }