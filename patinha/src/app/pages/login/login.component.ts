import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  loginForm: FormGroup;
  isLoginMode = true; // Alterna entre Login e Cadastro
  errorMsg: string = '';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      nome: [''], // Apenas para cadastro
      email: ['', [Validators.required, Validators.email]],
      whatsapp: [''], // Apenas para cadastro
      senha: ['', [Validators.required, Validators.minLength(6)]],
      confirmarSenha: [''] // Apenas para cadastro
    });
  }

  toggleMode(): void {
    this.isLoginMode = !this.isLoginMode;
    this.errorMsg = '';
    
    // Ajustar validadores dependendo do modo
    if (this.isLoginMode) {
      this.loginForm.get('nome')?.clearValidators();
      this.loginForm.get('confirmarSenha')?.clearValidators();
    } else {
      this.loginForm.get('nome')?.setValidators([Validators.required, Validators.minLength(3)]);
      this.loginForm.get('confirmarSenha')?.setValidators([Validators.required]);
    }
    this.loginForm.get('nome')?.updateValueAndValidity();
    this.loginForm.get('confirmarSenha')?.updateValueAndValidity();
  }

  onSubmit(): void {
    if (this.loginForm.valid) {
      const { email, senha, confirmarSenha } = this.loginForm.value;

      if (!this.isLoginMode && senha !== confirmarSenha) {
        this.errorMsg = 'As senhas não coincidem!';
        return;
      }

      this.authService.login(email);
      this.router.navigate(['/']);
    } else {
      this.errorMsg = 'Por favor, preencha todos os campos corretamente.';
    }
  }
}
