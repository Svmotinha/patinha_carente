import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Pet } from '../../components/pet-card/pet-card.component';

interface DetailedPet extends Pet {
  galeria: string[];
  historia: string;
  localResgate: string;
  tempoOng: string;
  vacinado: boolean;
  castrado: boolean;
}

@Component({
  selector: 'app-pet-detail',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './pet-detail.component.html',
  styleUrl: './pet-detail.component.scss'
})
export class PetDetailComponent implements OnInit {
  pet?: DetailedPet;
  mainImage: string = '';

  private allPets: DetailedPet[] = [
    {
      id: 1,
      nome: 'Fubá',
      tipo: 'Cachorro',
      idade: '2 anos',
      sexo: 'Macho',
      porte: 'Médio',
      descricao: 'Muito brincalhão e energético. Adora correr no parque e é ótimo com crianças.',
      imagem: 'assets/fotos-cachorro-fuba/1d888c7f-3216-4e5e-b745-c516f23b2a25.jpg',
      galeria: [
        'assets/fotos-cachorro-fuba/1d888c7f-3216-4e5e-b745-c516f23b2a25.jpg',
        'assets/fotos-cachorro-fuba/3bab1189-ffc1-48f0-ad6a-07502685c8e0.jpg',
        'assets/fotos-cachorro-fuba/92b9fd29-c1c4-42fa-b87c-900a64896fde.jpg'
      ],
      historia: 'Fubá foi encontrado em uma noite chuvosa próximo a um posto de gasolina. Estava muito assustado e magro, mas sua vontade de viver era maior. Após meses de cuidados e muito amor, ele se tornou esse cão radiante que você vê hoje.',
      localResgate: 'Posto de Gasolina - Centro',
      tempoOng: '6 meses',
      vacinado: true,
      castrado: true
    },
    {
      id: 2,
      nome: 'Plutos',
      tipo: 'Cachorro',
      idade: '3 anos',
      sexo: 'Macho',
      porte: 'Grande',
      descricao: 'Um protetor fiel e muito inteligente. Já sabe vários comandos!',
      imagem: 'assets/fotos-cachorro-plutos/2785d190-33d6-43d4-8af4-40ec525cb28e.jpg',
      galeria: [
        'assets/fotos-cachorro-plutos/2785d190-33d6-43d4-8af4-40ec525cb28e.jpg',
        'assets/fotos-cachorro-plutos/3e2f72d0-7f39-45b9-8ddd-efcb85d31bb6.jpg',
        'assets/fotos-cachorro-plutos/46cc56f2-a019-4295-b281-62bbd2f7d62e.jpg',
        'assets/fotos-cachorro-plutos/bd8f0652-934c-46b6-9084-854fe8c3ae82.jpg',
        'assets/fotos-cachorro-plutos/f52dbe9c-9bcb-48ca-85e9-44b0ff827c68.jpg'
      ],
      historia: 'Plutos vivia em uma obra abandonada. Os vizinhos o alimentavam, mas ele precisava de um lar de verdade. Ele é um cão que impõe respeito pelo tamanho, mas tem o coração de um filhote.',
      localResgate: 'Bairro Jardim América',
      tempoOng: '1 ano',
      vacinado: true,
      castrado: true
    },
    {
      id: 3,
      nome: 'Sherlock',
      tipo: 'Gato',
      idade: '2 anos',
      sexo: 'Macho',
      porte: 'Médio',
      descricao: 'Curioso e observador. Adora investigar cada cantinho da casa.',
      imagem: 'assets/fotos-gato-sherlock/12b65d1a-bef0-43d0-8303-577ef198247c.jpg',
      galeria: [
        'assets/fotos-gato-sherlock/12b65d1a-bef0-43d0-8303-577ef198247c.jpg',
        'assets/fotos-gato-sherlock/370fd0b9-043d-4e68-aa0a-b688b5c81e6a.jpg',
        'assets/fotos-gato-sherlock/5672a7b0-1e5f-4344-9157-84efdd2b46ba.jpg',
        'assets/fotos-gato-sherlock/fe5a9e08-0e17-476e-b671-d16eeb276686.jpg'
      ],
      historia: 'Encontrado dentro de um bueiro tentando se proteger do frio. Sherlock recebeu esse nome porque nunca para quieto até descobrir de onde vem qualquer barulhinho novo.',
      localResgate: 'Rua das Flores',
      tempoOng: '4 meses',
      vacinado: true,
      castrado: true
    }
  ];

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.pet = this.allPets.find(p => p.id === id);
    if (this.pet) {
      this.mainImage = this.pet.imagem;
    }
  }

  changeImage(img: string): void {
    this.mainImage = img;
  }

  compartilhar(): void {
    if (navigator.share) {
      navigator.share({
        title: `Conheça o ${this.pet?.nome}!`,
        text: `Olha que fofura o ${this.pet?.nome} que está para adoção na Patinha Carente!`,
        url: window.location.href
      }).catch(console.error);
    } else {
      const text = `Confira esse pet para adoção: ${window.location.href}`;
      window.open(`https://api.whatsapp.com/send?text=${encodeURIComponent(text)}`, '_blank');
    }
  }
}
