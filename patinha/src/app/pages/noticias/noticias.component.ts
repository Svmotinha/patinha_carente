import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

interface Noticia {
  id: number;
  titulo: string;
  resumo: string;
  data: string;
  fonte: string;
  link: string;
  categoria: 'Campanha' | 'Resgate' | 'Ação Social';
  imagemPlaceholder: string;
}

@Component({
  selector: 'app-noticias',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './noticias.component.html',
  styleUrl: './noticias.component.scss'
})
export class NoticiasComponent implements OnInit {
  noticias: Noticia[] = [
    {
      id: 1,
      titulo: 'Cuidado de 43 Lhamas Apreendidas no Acre',
      resumo: 'A ONG assumiu a responsabilidade por 43 lhamas apreendidas na BR-364. Os animais estão recebendo alimentação e cuidados veterinários em um abrigo provisório enquanto aguardam decisão judicial.',
      data: '09/06/2026',
      fonte: 'G1 Acre',
      link: 'https://g1.globo.com/ac/acre/noticia/2026/06/09/apos-20-dias-lhamas-apreendidas-no-ac-seguem-em-abrigo-provisorio-a-espera-de-decisao-judicial.ghtml',
      categoria: 'Resgate',
      imagemPlaceholder: '🦙'
    },
    {
      id: 2,
      titulo: 'Parceria com Galvez: Campanha "O Legado do Pirata"',
      resumo: 'Em homenagem ao cão Pirata, o clube Galvez e a Patinha Carente lançaram uma campanha de incentivo à adoção. Jogadores entraram em campo com animais para dar visibilidade à causa.',
      data: '30/01/2026',
      fonte: 'GE Globo',
      link: 'https://ge.globo.com/ac/futebol/times/galvez/noticia/2026/01/30/apos-morte-de-cao-xodo-galvez-lanca-campanha-de-incentivo-a-adocao-de-cachorros.ghtml',
      categoria: 'Campanha',
      imagemPlaceholder: '⚽'
    },
    {
      id: 3,
      titulo: 'Justiça pelo Cão Orelha: Protesto em Rio Branco',
      resumo: 'Organizamos uma manifestação em frente ao Palácio Rio Branco para cobrar justiça por maus-tratos e alertar sobre a violência contra animais em nossa cidade.',
      data: '28/01/2026',
      fonte: 'ac24horas',
      link: 'https://ac24horas.com/2026/01/28/ong-organiza-manifestacao-em-rio-branco-para-cobrar-justica-por-cao-vitima-de-maus-tratos/',
      categoria: 'Ação Social',
      imagemPlaceholder: '📢'
    },
    {
      id: 4,
      titulo: 'Nosso guerreiro Fubá venceu mais uma batalha!',
      resumo: 'Acompanhe a história de superação do Fubá, que foi resgatado em estado crítico e hoje esbanja alegria e saúde.',
      data: 'Instagram',
      fonte: 'Instagram',
      link: 'https://www.instagram.com/p/DYyU06jM_nV/',
      categoria: 'Resgate',
      imagemPlaceholder: '🐕'
    }
  ];

  ongLinks = {
    instagram: 'https://www.instagram.com/patinhacarente/',
    facebook: 'https://www.facebook.com/carentepatinha/',
    whatsappGroup: 'https://chat.whatsapp.com/JAVDksby8Xb1UgwzNhHfAr?mode=wwc',
    pix: '21.614.179/0001-47'
  };

  constructor() {}

  ngOnInit(): void {}

  copyPix(): void {
    navigator.clipboard.writeText(this.ongLinks.pix).then(() => {
      alert('Chave PIX copiada!');
    });
  }
}
