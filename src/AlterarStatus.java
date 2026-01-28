import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class AlterarStatus {
    public void executar(List<Tarefa> lista) {
        if (lista.isEmpty()) {
            System.out.println(Cores.erro("Não há tarefas para alterar."));
            return;
        }

        // --- MELHORIA: STREAMS ---
        // Usamos IntStream para gerar os índices e exibir as tarefas de forma elegante
        System.out.println(Cores.AZUL + "\n--- SELECIONE A TAREFA ---" + Cores.RESET);
        IntStream.range(0, lista.size())
                .forEach(i -> System.out.println(i + " - " + lista.get(i)));

        int indice = ConsoleInput.lerInteiro("\nDigite o número da tarefa que deseja alterar: ");

        // --- MELHORIA: OPTIONAL ---
        // Validamos a existência da tarefa de forma segura
        buscarTarefaPorIndice(lista, indice).ifPresentOrElse(tarefa -> {
            exibirOpcoesStatus();
            int opcao = ConsoleInput.lerInteiro("Opção: ");

            // Aplicamos a mudança usando a lógica de Status
            atualizarStatusTarefa(tarefa, opcao);

        }, () -> System.out.println(Cores.erro("Tarefa não encontrada para o índice: " + indice)));
    }

    // Método auxiliar usando Optional para garantir segurança no acesso à lista
    private Optional<Tarefa> buscarTarefaPorIndice(List<Tarefa> lista, int indice) {
        if (indice >= 0 && indice < lista.size()) {
            return Optional.of(lista.get(indice));
        }
        return Optional.empty();
    }

    private void exibirOpcoesStatus() {
        System.out.println("\nEscolha o novo status:");
        System.out.println("1. " + Status.PENDENTE);
        System.out.println("2. " + Status.EM_ANDAMENTO);
        System.out.println("3. " + Status.CONCLUIDA);
    }

    private void atualizarStatusTarefa(Tarefa tarefa, int opcao) {
        switch (opcao) {
            case 1 -> tarefa.setStatus(Status.PENDENTE);
            case 2 -> tarefa.setStatus(Status.EM_ANDAMENTO);
            case 3 -> tarefa.setStatus(Status.CONCLUIDA);
            default -> {
                System.out.println(Cores.erro("Opção inválida. Nenhuma alteração realizada."));
                return;
            }
        }
        System.out.println(Cores.sucesso("Status atualizado com sucesso para: " + tarefa.getStatus()));
    }
}