import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PetCardComponent, Pet } from '../../components/pet-card/pet-card.component';

@Component({
  selector: 'app-pets',
  standalone: true,
  imports: [CommonModule, PetCardComponent, FormsModule],
  templateUrl: './pets.component.html',
  styleUrl: './pets.component.scss'
})
export class PetsComponent implements OnInit {
  // Lista original completa
  allPets: Pet[] = [
    {
      id: 1,
      nome: 'Fubá',
      tipo: 'Cachorro',
      idade: '2 anos',
      sexo: 'Macho',
      porte: 'Médio',
      descricao: 'Muito brincalhão e energético. Adora correr no parque e é ótimo com crianças.',
      imagem: 'assets/fotos-cachorro-fuba/1d888c7f-3216-4e5e-b745-c516f23b2a25.jpg'
    },
    {
      id: 2,
      nome: 'Plutos',
      tipo: 'Cachorro',
      idade: '3 anos',
      sexo: 'Macho',
      porte: 'Grande',
      descricao: 'Um protetor fiel e muito inteligente. Já sabe vários comandos!',
      imagem: 'assets/fotos-cachorro-plutos/2785d190-33d6-43d4-8af4-40ec525cb28e.jpg'
    },
    {
      id: 3,
      nome: 'Sherlock',
      tipo: 'Gato',
      idade: '2 anos',
      sexo: 'Macho',
      porte: 'Médio',
      descricao: 'Curioso e observador. Adora investigar cada cantinho da casa.',
      imagem: 'assets/fotos-gato-sherlock/12b65d1a-bef0-43d0-8303-577ef198247c.jpg'
    },
    {
      id: 4,
      nome: 'Miau',
      tipo: 'Gato',
      idade: '1 ano',
      sexo: 'Fêmea',
      porte: 'Pequeno',
      descricao: 'Dócil e carinhosa. Uma gatinha muito tranquila e amigável.',
      imagem: 'assets/fotos-gato-sherlock/370fd0b9-043d-4e68-aa0a-b688b5c81e6a.jpg'
    }
  ];

  // Lista filtrada que é exibida na tela
  filteredPets: Pet[] = [];

  // Filtros
  searchTerm: string = '';
  selectedTipo: string = 'Todos';
  selectedPorte: string = 'Todos';
  selectedSexo: string = 'Todos';
  selectedIdade: string = 'Todos';

  constructor() { }

  ngOnInit(): void {
    this.filteredPets = [...this.allPets];
  }

  applyFilters(): void {
    this.filteredPets = this.allPets.filter(pet => {
      const matchesSearch = pet.nome.toLowerCase().includes(this.searchTerm.toLowerCase());
      const matchesTipo = this.selectedTipo === 'Todos' || pet.tipo === this.selectedTipo;
      const matchesPorte = this.selectedPorte === 'Todos' || pet.porte === this.selectedPorte;
      const matchesSexo = this.selectedSexo === 'Todos' || pet.sexo === this.selectedSexo;
      
      // Simplificando a lógica de idade para o mock (Filhotinho vs Adulto)
      let matchesIdade = true;
      if (this.selectedIdade !== 'Todos') {
        const idadeNum = parseInt(pet.idade);
        if (this.selectedIdade === 'Filhote') matchesIdade = idadeNum < 1;
        else if (this.selectedIdade === 'Adulto') matchesIdade = idadeNum >= 1 && idadeNum < 7;
        else if (this.selectedIdade === 'Sênior') matchesIdade = idadeNum >= 7;
      }
      
      return matchesSearch && matchesTipo && matchesPorte && matchesSexo && matchesIdade;
    });
  }

  resetFilters(): void {
    this.searchTerm = '';
    this.selectedTipo = 'Todos';
    this.selectedPorte = 'Todos';
    this.selectedSexo = 'Todos';
    this.selectedIdade = 'Todos';
    this.applyFilters();
  }
}
