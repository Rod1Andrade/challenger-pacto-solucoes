import { NgModule } from "@angular/core";
import { LoginComponent } from "./login.component";
import { ReactiveFormsModule } from "@angular/forms";
import { CommonModule } from "@angular/common";

@NgModule({
    declarations: [
        LoginComponent,
    ],
    imports: [ReactiveFormsModule, CommonModule],
})
export class LoginModule { }