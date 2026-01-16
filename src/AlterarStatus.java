import java.util.List;

public class AlterarStatus {
    public void executar(List<Tarefa> lista) {
        if (lista.isEmpty()) {
            System.out.println("Não há tarefas para alterar.");
            return;
        }

        // Listar tarefas com índice para o usuário escolher
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(i + " - " + lista.get(i));
        }

        int indice = ConsoleInput.lerInteiro("Digite o número da tarefa que deseja alterar: ");

        if (indice >= 0 && indice < lista.size()) {
            System.out.println("Escolha o novo status:");
            System.out.println("1. PENDENTE | 2. EM ANDAMENTO | 3. CONCLUIDA");
            int opcao = ConsoleInput.lerInteiro("Opção: ");

            switch (opcao) {
                case 1 -> lista.get(indice).setStatus(Status.PENDENTE);
                case 2 -> lista.get(indice).setStatus(Status.EM_ANDAMENTO);
                case 3 -> lista.get(indice).setStatus(Status.CONCLUIDA);
                default -> System.out.println("Opção inválida.");
            }
            System.out.println("Status atualizado!");
        } else {
            System.out.println("Tarefa não encontrada.");
        }
    }
}