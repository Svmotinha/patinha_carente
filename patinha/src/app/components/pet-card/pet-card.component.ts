import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

export interface Pet {
  id: number;
  nome: string;
  tipo: 'Cachorro' | 'Gato';
  idade: string;
  sexo: 'Macho' | 'Fêmea';
  porte: 'Pequeno' | 'Médio' | 'Grande';
  descricao: string;
  imagem: string;
}

@Component({
  selector: 'app-pet-card',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './pet-card.component.html',
  styleUrl: './pet-card.component.scss'
})
export class PetCardComponent {
  @Input() pet!: Pet;
}
