import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Inicializa o banco de dados (carregando do arquivo tarefas.txt)
        ArquivoTarefas arquivo = new ArquivoTarefas();
        List<Tarefa> bancoDeDados = arquivo.carregar();

        // 2. Instancia as classes de serviço
        AvisosProximos avisador = new AvisosProximos();
        CadastrarTarefa cadastrador = new CadastrarTarefa();
        ListarTarefas listador = new ListarTarefas();
        FiltrarTarefas filtrador = new FiltrarTarefas();
        AlterarStatus alterador = new AlterarStatus();
        RemoverTarefa removedor = new RemoverTarefa();

        // 3. Início do programa e Notificações de Vencimento
        System.out.println("==========================================");
        System.out.println("     SISTEMA DE GERENCIAMENTO DE TAREFAS  ");
        System.out.println("==========================================");

        // Exibe alertas de tarefas vencidas ou para amanhã logo na entrada
        avisador.verificarVencimentos(bancoDeDados);

        int opcao = -1;

        // 4. Loop Principal
        while (opcao != 0) {
            exibirMenu();
            opcao = ConsoleInput.lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> {
                    cadastrador.executar(bancoDeDados);
                    arquivo.salvar(bancoDeDados); // Salva as alterações no arquivo
                }
                case 2 -> listador.exibir(bancoDeDados);
                case 3 -> menuFiltros(filtrador, bancoDeDados);
                case 4 -> {
                    alterador.executar(bancoDeDados);
                    arquivo.salvar(bancoDeDados); // Salva as alterações no arquivo
                }
                case 5 -> {
                    removedor.executar(bancoDeDados);
                    arquivo.salvar(bancoDeDados); // Salva as alterações no arquivo
                }
                case 0 -> System.out.println("Encerrando e salvando... Até logo!");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    // Método para organizar o menu principal
    private static void exibirMenu() {
        System.out.println("\n[MENU PRINCIPAL]");
        System.out.println("1. Cadastrar Tarefa");
        System.out.println("2. Listar Todas as Tarefas");
        System.out.println("3. Filtrar Tarefas (Título/Data)");
        System.out.println("4. Alterar Status");
        System.out.println("5. Deletar Tarefa");
        System.out.println("0. Sair");
        System.out.println("------------------------------");
    }

    // Método para organizar o sub-menu de filtros
    private static void menuFiltros(FiltrarTarefas filtrador, List<Tarefa> lista) {
        System.out.println("\n--- OPÇÕES DE FILTRO ---");
        System.out.println("1. Por Título");
        System.out.println("2. Por Data");
        int subOpcao = ConsoleInput.lerInteiro("Escolha o filtro: ");

        if (subOpcao == 1) {
            filtrador.porTitulo(lista);
        } else if (subOpcao == 2) {
            filtrador.porData(lista);
        } else {
            System.out.println("Opção de filtro inválida.");
        }
    }
}