import java.util.List;

public class ListarTarefas {
    public void exibir(List<Tarefa> lista) {
        System.out.println("\n--- SUAS TAREFAS ---");

        if (lista.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
            return;
        }

        for (int i = 0; i < lista.size(); i++) {
            Tarefa t = lista.get(i);
            System.out.print(i + " - " + t);

            // Adiciona um detalhe visual extra na listagem
            if (t.isVencida()) {
                System.out.println(" <-- ATENÇÃO!");
            } else {
                System.out.println();
            }
        }
    }
}