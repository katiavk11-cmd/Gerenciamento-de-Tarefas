import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        // 1. INICIALIZAÇÃO E CARREGAMENTO
        ArquivoTarefas gerenciadorArquivo = new ArquivoTarefas();
        List<Tarefa> bancoDeDados = gerenciadorArquivo.carregar();

        // 2. INSTANCIANDO DOS SERVIÇOS
        AvisosProximos avisa = new AvisosProximos();
        CadastrarTarefa cadastro = new CadastrarTarefa();
        ListarTarefas lista = new ListarTarefas();
        FiltrarTarefas filtra = new FiltrarTarefas();
        AlterarStatus altera = new AlterarStatus();
        RemoverTarefa remove = new RemoverTarefa();

        // 3. BOAS-VINDAS E ALERTAS INICIAIS
        System.out.println(Cores.AZUL + "==========================================" + Cores.RESET);
        System.out.println(Cores.NEGRITO + "     SISTEMA DE GESTÃO DE TAREFAS v2.0    " + Cores.RESET);
        System.out.println(Cores.AZUL + "==========================================" + Cores.RESET);

        // --- MELHORIA: EXECUÇÃO ASSÍNCRONA ---
        // O aviso de vencimentos roda em segundo plano para não atrasar a abertura do menu
        CompletableFuture.runAsync(() -> avisa.verificarVencimentos(bancoDeDados));

        int opcao = -1;

        // 4. LOOP PRINCIPAL DO MENU
        while (opcao != 0) {
            exibirMenu();
            opcao = ConsoleInput.lerInteiro(Cores.NEGRITO + "Escolha uma opção: " + Cores.RESET);

            switch (opcao) {
                case 1 -> {
                    cadastro.executar(bancoDeDados);
                    gerenciadorArquivo.salvar(bancoDeDados);
                }
                case 2 -> {
                    // MELHORIA: Listador agora deve usar Streams internamente para não mutar a lista original
                    lista.exibir(bancoDeDados);
                }
                case 3 -> {
                    executarMenuFiltro(filtra, bancoDeDados);
                }
                case 4 -> {
                    altera.executar(bancoDeDados);
                    gerenciadorArquivo.salvar(bancoDeDados);
                }
                case 5 -> {
                    remove.executar(bancoDeDados);
                    gerenciadorArquivo.salvar(bancoDeDados);
                }
                case 0 -> System.out.println(Cores.sucesso("\nSistema encerrado com segurança. Até breve!"));

                default -> System.out.println(Cores.erro("Opção inválida! Tente novamente."));
            }
        }
    }

    // --- MÉTODOS AUXILIARES PARA ORGANIZAÇÃO DO CÓDIGO ---

    private static void exibirMenu() {
        System.out.println("\n" + Cores.AZUL + "--- MENU PRINCIPAL ---" + Cores.RESET);
        System.out.println("1. " + Cores.NEGRITO + "Cadastrar" + Cores.RESET + " Nova Tarefa");
        System.out.println("2. " + Cores.NEGRITO + "Listar" + Cores.RESET + " Todas (Ordenadas por Prazo)");
        System.out.println("3. " + Cores.NEGRITO + "Filtrar" + Cores.RESET + " Busca (Título, Data ou Status)");
        System.out.println("4. " + Cores.NEGRITO + "Alterar Status" + Cores.RESET);
        System.out.println("5. " + Cores.NEGRITO + "Remover" + Cores.RESET + " Tarefa");
        System.out.println("0. Sair");
        System.out.println("-----------------------");
    }

    private static void executarMenuFiltro(FiltrarTarefas filtrador, List<Tarefa> lista) {
        System.out.println("\n" + Cores.AMARELO + "--- OPÇÕES DE FILTRO ---" + Cores.RESET);
        System.out.println("1. Por Título");
        System.out.println("2. Por Data Específica");
        System.out.println("3. Por Status");
        System.out.println("4. Voltar ao Menu");

        int tipo = ConsoleInput.lerInteiro("Opção de filtro: ");

        // MELHORIA: Os métodos abaixo serão refatorados internamente para usar Predicates e Streams
        switch (tipo) {
            case 1 -> filtrador.porTitulo(lista);
            case 2 -> filtrador.porData(lista);
            case 3 -> filtrador.porStatus(lista);
            case 4 -> System.out.println("Voltando...");
            default -> System.out.println(Cores.erro("Filtro inexistente."));
        }
    }
}