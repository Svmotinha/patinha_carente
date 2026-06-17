-- Script de Criação do Banco de Dados: Patinha Carente
-- Engine: MySQL

CREATE DATABASE IF NOT EXISTS patinha_carente;
USE patinha_carente;

-- 1. Tabela de Usuários
CREATE TABLE IF NOT EXISTS usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    whatsapp VARCHAR(20),
    cidade VARCHAR(100),
    role ENUM('USER', 'ADMIN') DEFAULT 'USER',
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Tabela de Pets
CREATE TABLE IF NOT EXISTS pet (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    especie ENUM('Cachorro', 'Gato') NOT NULL,
    raca VARCHAR(50),
    porte ENUM('Pequeno', 'Médio', 'Grande') NOT NULL,
    sexo ENUM('Macho', 'Fêmea') NOT NULL,
    idade_estimada VARCHAR(50),
    descricao TEXT,
    historia TEXT,
    local_resgate VARCHAR(255),
    tempo_ong VARCHAR(50),
    vacinado BOOLEAN DEFAULT FALSE,
    castrado BOOLEAN DEFAULT FALSE,
    status_adocao ENUM('Disponível', 'Em Processo', 'Adotado') DEFAULT 'Disponível',
    
    -- Campos solicitados para saúde e apadrinhamento
    condicao_saude TEXT, 
    urgencia_apadrinhamento BOOLEAN DEFAULT FALSE,
    
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3. Tabela de Fotos (Galeria dos Pets)
CREATE TABLE IF NOT EXISTS foto_pet (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pet_id BIGINT NOT NULL,
    url_imagem VARCHAR(255) NOT NULL,
    is_principal BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (pet_id) REFERENCES pet(id) ON DELETE CASCADE
);

-- 4. Tabela de Interesses em Adoção
CREATE TABLE IF NOT EXISTS interesse_adocao (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    pet_id BIGINT NOT NULL,
    motivo TEXT NOT NULL,
    status_solicitacao ENUM('Pendente', 'Confirmado', 'Cancelado', 'Concluído') DEFAULT 'Pendente',
    data_solicitacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_visita DATETIME,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (pet_id) REFERENCES pet(id) ON DELETE CASCADE
);

-- 5. Tabela de Doações e Apadrinhamentos
CREATE TABLE IF NOT EXISTS doacao_apadrinhamento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT,
    pet_id BIGINT, -- Nulo se for doação geral para a ONG
    valor DECIMAL(10, 2) NOT NULL,
    tipo ENUM('Doação Única', 'Apadrinhamento Mensal') NOT NULL,
    data_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE SET NULL,
    FOREIGN KEY (pet_id) REFERENCES pet(id) ON DELETE SET NULL
);

-- 6. Tabela de Notícias
CREATE TABLE IF NOT EXISTS noticia (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    conteudo TEXT NOT NULL,
    resumo VARCHAR(500),
    url_imagem VARCHAR(255),
    link_detalhes VARCHAR(255),
    categoria ENUM('Campanha', 'Resgate', 'Acao_Social') NOT NULL,
    data_publicacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    autor_id BIGINT,
    FOREIGN KEY (autor_id) REFERENCES usuario(id) ON DELETE SET NULL
);
