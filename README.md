📝 Gerenciador de Tarefas (Java CLI v2.0)
Este é um sistema de linha de comando (CLI) desenvolvido em Java 17+ para gerenciamento de produtividade pessoal. O projeto foi refatorado para aplicar as melhores práticas de desenvolvimento, unindo a Programação Orientada a Objetos (POO) à Programação Funcional e ao Assincronismo.

🚀 Funcionalidades Modernizadas
Ciclo de Vida Completo (CRUD): Criar, listar, filtrar, atualizar status e remover tarefas com uma interface colorida e intuitiva.

Processamento Assíncrono: O sistema de alertas utiliza CompletableFuture para verificar prazos em segundo plano, permitindo que o menu principal seja carregado sem latência.

Painel de Atenção Dinâmico: Identifica automaticamente tarefas que vencem no dia seguinte e exibe alertas visuais de "VENCIDA" para itens expirados.

Busca com Streams: Filtros inteligentes que permitem localizar tarefas por partes do título, datas específicas ou status, utilizando a eficiência da Streams API.

Confirmação de Segurança: Implementação de "Double Check" em operações críticas, como a remoção de tarefas.

💾 Persistência e Inteligência de Dados
O sistema gerencia um estado persistente através do arquivo tarefas.txt:

Sincronização Reativa: Qualquer alteração no estado da lista (adição, mudança de status ou remoção) dispara automaticamente a persistência em disco.

Arquitetura Funcional de I/O: O carregamento de dados utiliza BufferedReader.lines() e Stream.map(), transformando registros textuais em objetos de forma declarativa e segura.

🛠️ Tecnologias e Conceitos de Engenharia
Linguagem: Java 17+ (aproveitando Switch Expressions e Text Blocks).

Programação Funcional: Uso intensivo de Lambdas, Method References, Predicates e Comparators.

Robustez: Implementação de Java Optional para eliminar riscos de NullPointerException.

Manipulação de Datas: API java.time para cálculos precisos de prazos e formatação brasileira (dd/MM/yyyy).

Design Patterns & Princípios:

SRP (Single Responsibility): Cada serviço (Filtrar, Listar, Arquivo) possui uma única responsabilidade.

DRY (Don't Repeat Yourself): Centralização de lógicas de filtro e mensagens de erro.

Encapsulamento Avançado: Uso de Enums inteligentes que carregam comportamentos e estilos visuais.

📂 Estrutura do Projeto
Main.java: Orquestrador assíncrono do sistema.

Tarefa.java: Entidade com lógica de negócio para cálculo de vencimentos.

Status.java: Enum inteligente com metadados de cores e estados.

ArquivoTarefas.java: Gerente de persistência funcional.

ConsoleInput.java: Utilitário blindado para captura e validação de entradas.

Cores.java: Classe utilitária final para padronização visual ANSI.

Serviços (*.java): Classes especializadas para Cadastrar, Listar, Filtrar, Alterar e Remover.

🔧 Como Executar
Certifique-se de ter o JDK 17 ou superior.

Compile o projeto:

Bash

javac -d bin src/*.java
Execute a aplicação:

Bash

java -cp bin Main
