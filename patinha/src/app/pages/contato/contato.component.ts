import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-contato',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './contato.component.html',
  styleUrl: './contato.component.scss'
})
export class ContatoComponent implements OnInit {
  contactForm!: FormGroup;
  submitted = false;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.contactForm = this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(3)]],
      assunto: ['Dúvida Geral', Validators.required],
      mensagem: ['', [Validators.required, Validators.minLength(10)]]
    });
  }

  onSubmit(): void {
    if (this.contactForm.valid) {
      const formData = this.contactForm.value;
      const whatsappNumber = '5511999999999'; // Substitua pelo número real da ONG
      
      const message = `Olá! Gostaria de falar com a Patinha Carente:
*Assunto:* ${formData.assunto}
*Nome:* ${formData.nome}
*Mensagem:* ${formData.mensagem}`;

      const whatsappUrl = `https://api.whatsapp.com/send?phone=${whatsappNumber}&text=${encodeURIComponent(message)}`;
      
      this.submitted = true;
      
      setTimeout(() => {
        window.open(whatsappUrl, '_blank');
        this.submitted = false;
        this.contactForm.reset({assunto: 'Dúvida Geral'});
      }, 1500);
    } else {
      this.contactForm.markAllAsTouched();
    }
  }
}
