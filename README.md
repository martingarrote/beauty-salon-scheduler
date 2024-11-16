# beauty-salon-scheduler

Projeto acadêmico desenvolvido durante a disciplina de Projeto Aplicado I (AEx).

Grupo: Martin Dambrowski Stabel Garrote, Nattan Mendes Tinonin

## Pré-requisitos

Lista das ferramentas necessárias para instalação e execução do projeto:
- Git
- Java 17
- Maven
- Docker
    - Docker Compose
    - Imagem do PostgreSQL

## Instalação e Execução

1. Clone o repositório

```bash
git clone https://github.com/martingarrote/beauty-salon-scheduler.git
```

2. Acesse o diretório do projeto

```bash
cd book-lend
```

3. Prontifique o PostgreSQL via Docker Compose

```bash
docker-compose up -d
```

4. Configure as dependências do projeto

```bash
mvn clean install
```

5. Execute a aplicação

```bash
mvn spring-boot:run
```
