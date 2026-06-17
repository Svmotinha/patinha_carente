import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-adoption-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './adoption-form.component.html',
  styleUrl: './adoption-form.component.scss'
})
export class AdoptionFormComponent implements OnInit {
  adoptionForm!: FormGroup;
  petId: number | null = null;
  petName: string | null = null;
  submitted = false;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(params => {
      this.petId = Number(params.get('id'));
      this.petName = params.get('name');
    });

    this.adoptionForm = this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      whatsapp: ['', [Validators.required, Validators.pattern(/^\d{10,11}$/)]],
      cidade: ['', Validators.required],
      temOutrosPets: ['Não', Validators.required],
      motivo: ['', [Validators.required, Validators.minLength(20)]]
    });
  }

  onSubmit(): void {
    if (this.adoptionForm.valid) {
      const formData = this.adoptionForm.value;
      const whatsappNumber = '68992114188'; // Substitua pelo número real da ONG
      
      const message = `Olá! Tenho interesse em adotar um pet:
*Pet:* ${this.petName || 'Não especificado'}
*Nome:* ${formData.nome}
*E-mail:* ${formData.email}
*WhatsApp:* ${formData.whatsapp}
*Cidade:* ${formData.cidade}
*Possui outros pets:* ${formData.temOutrosPets}
*Motivo:* ${formData.motivo}`;

      const whatsappUrl = `https://api.whatsapp.com/send?phone=${whatsappNumber}&text=${encodeURIComponent(message)}`;
      
      this.submitted = true;
      
      // Pequeno delay para mostrar a animação de sucesso antes de redirecionar
      setTimeout(() => {
        window.open(whatsappUrl, '_blank');
      }, 1500);
    } else {
      this.adoptionForm.markAllAsTouched();
    }
  }
}
