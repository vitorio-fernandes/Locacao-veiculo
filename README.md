Sistema de Gestão de Locação de Veículos
Este projeto é um sistema de gestão de locação de veículos desenvolvido em Java com Spring Boot. Ele permite a administração de uma frota de veículos, autenticação JWT, gestão de reservas, e cálculo de tarifas, com controle de acesso para diferentes perfis de usuário (Admin e Cliente).

Índice
Funcionalidades
Tecnologias Utilizadas
Estrutura do Projeto
Endpoints Principais
Como Executar o Projeto
Estrutura de Papéis e Permissões
Melhorias Futuras
Contribuição
Licença
Funcionalidades
Autenticação e Autorização: Sistema de login com autenticação JWT, garantindo segurança para o acesso de diferentes tipos de usuários.
Gestão de Frota: Controle de cadastro, atualização, e exclusão de veículos, além de informações de disponibilidade e manutenção.
Reservas de Veículos: Permite que clientes façam reservas de veículos e que o sistema calcule automaticamente as tarifas baseadas no tempo de locação.
Cálculo de Tarifas: Tarifas de locação são calculadas automaticamente com base na duração da reserva.
Controle de Acesso: O sistema define permissões de acesso para administradores (Admin) e clientes, assegurando uma gestão eficiente.
Tecnologias Utilizadas
Java 17
Spring Boot 3.3.4
Spring Security: Implementação de autenticação JWT
Maven: Para gerenciamento de dependências e build
Banco de Dados: [Adicione aqui o banco de dados utilizado, como MySQL, PostgreSQL, etc.]
Estrutura do Projeto
Controller: Controladores responsáveis pela lógica das rotas e endpoints do sistema.
AuthController: Gerencia a autenticação de usuários.
AdminController: Controla operações restritas aos administradores.
ClienteController: Gerencia operações dos clientes.
DTO (Data Transfer Objects): Facilita a transferência de dados entre cliente e servidor.
Services: Implementação das regras de negócio para cada funcionalidade.
Repository: Interfaces para interação com o banco de dados.
Endpoints Principais
Autenticação (/auth)
POST /auth/login: Realiza a autenticação do usuário e retorna um token JWT.
Gestão de Usuários (/admin/users)
GET /admin/users: Lista todos os usuários (restrito a Admins).
PUT /admin/users/{id}: Atualiza as informações de um usuário específico (restrito a Admins).
Gestão de Veículos (/vehicles)
POST /vehicles: Adiciona um novo veículo (restrito a Admins).
PUT /vehicles/{id}: Atualiza os dados de um veículo (restrito a Admins).
DELETE /vehicles/{id}: Remove um veículo do sistema (restrito a Admins).
Reservas (/reservas)
POST /reservas: Cria uma nova reserva de veículo.
GET /reservas: Lista as reservas feitas pelo cliente autenticado.
Como Executar o Projeto
Clone o repositório:

bash
Copiar código
git clone https://github.com/seuusuario/LocacaoVeiculos.git
cd LocacaoVeiculos
Compile o projeto com Maven:

bash
Copiar código
./mvnw clean install
Inicie o projeto:

bash
Copiar código
./mvnw spring-boot:run
O projeto estará disponível em http://localhost:8080.

Estrutura de Papéis e Permissões
Admin: Acesso completo ao sistema, incluindo gerenciamento de usuários, veículos, e manutenção do sistema.
Cliente: Acesso às funcionalidades de reserva de veículos e visualização de informações relacionadas.
Melhorias Futuras
Implementação de relatórios detalhados de locações.
Integração com sistemas de pagamento para automatizar o processo de cobrança.
Suporte para múltiplas agências de locação.
