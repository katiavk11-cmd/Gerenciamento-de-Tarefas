import java.util.List;

public class RemoverTarefa {
    public void executar(List<Tarefa> lista) {
        if (lista.isEmpty()) {
            System.out.println("A lista está vazia. Nada para deletar.");
            return;
        }

        System.out.println("\n--- REMOVER TAREFA ---");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(i + " - " + lista.get(i).getTitulo());
        }

        int indice = ConsoleInput.lerInteiro("Digite o número da tarefa que deseja apagar: ");

        if (indice >= 0 && indice < lista.size()) {
            Tarefa removida = lista.remove(indice);
            System.out.println("Tarefa '" + removida.getTitulo() + "' deletada com sucesso!");
        } else {
            System.out.println("Índice inválido.");
        }
    }
}