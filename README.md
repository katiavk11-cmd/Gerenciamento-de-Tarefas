# 📝 Gerenciador de Tarefas Profissional (Java CLI)

Este é um sistema de linha de comando (CLI) desenvolvido em Java para gerenciamento de produtividade pessoal. O projeto aplica conceitos sólidos de **Programação Orientada a Objetos (POO)** e garante que nenhuma informação seja perdida através de um sistema de persistência em arquivos locais.

## 🚀 Funcionalidades

* **Ciclo de Vida Completo (CRUD):** Criar, listar, filtrar, atualizar status e remover tarefas de forma intuitiva.
* **Validação de Dados:** Filtros de segurança que impedem o cadastro de títulos com menos de 5 caracteres ou campos vazios.
* **Gestão de Status:** Controle através de estados específicos: `PENDENTE`, `EM_ANDAMENTO` e `CONCLUÍDA`.
* **Sistema de Alertas Proativo:**
* Exibe um resumo de tarefas que vencem no dia seguinte ao iniciar o sistema.
* Sinaliza visualmente tarefas com **PRAZO VENCIDO** na listagem geral.


* **Busca Inteligente:** Filtros para localizar tarefas rapidamente por palavra-chave no título ou por uma data específica.

---

## 💾 Persistência de Dados (O Arquivo `tarefas.txt`)

O grande diferencial deste sistema é o gerenciamento de memória persistente.

* **Armazenamento Estruturado:** Os dados não desaparecem ao fechar o programa. Eles são salvos no arquivo `tarefas.txt` na raiz do projeto.
* **Formato Delimitado:** O sistema utiliza um padrão CSV (separado por `;`), salvando os campos: `Título;Descrição;Data;Status`.
* **Sincronização Reativa:** Sempre que uma tarefa é cadastrada, removida ou tem seu status alterado, o arquivo é atualizado automaticamente.
* **Recuperação de Dados:** Ao iniciar, o software lê o arquivo, processa as strings e reconstrói a lista de objetos na memória RAM.

---

## 🛠️ Tecnologias e Conceitos Utilizados

* **Linguagem:** Java 17+
* **Persistência:** Java I/O (`java.io.PrintWriter`, `java.io.BufferedReader`).
* **Manipulação de Datas:** API `java.time.LocalDate` e `DateTimeFormatter`.
* **Padrões de Projeto:** * **Responsabilidade Única (SRP):** Cada classe possui uma função bem definida (ex: uma classe apenas para filtros, outra apenas para arquivos).
* **Encapsulamento:** Proteção de atributos sensíveis na classe `Tarefa`.



---

## 📂 Estrutura das Classes

* `Main.java`: Ponto de entrada e orquestrador do menu.
* `Tarefa.java`: Entidade principal com lógica de negócio (vencimentos).
* `Status.java`: Enumeração dos estados da tarefa.
* `ArquivoTarefas.java`: Gerente de leitura e escrita no disco.
* `ConsoleInput.java`: Utilitário para captura e validação de entradas do teclado.
* `AvisosProximos.java`: Sistema de notificações de prazos.
* `Cadastrar/Listar/Filtrar/Alterar/RemoverTarefa.java`: Classes de serviço que executam as ações do usuário.

---

## 🔧 Como Executar

1. Certifique-se de ter o **JDK 17** ou superior instalado.
2. Compile todos os arquivos da pasta `src`:
```bash
javac *.java

3. Execute a classe principal:
```bash
java Main





