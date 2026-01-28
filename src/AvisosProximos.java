import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AvisosProximos {

    public void verificarVencimentos(List<Tarefa> tarefas) {
        LocalDate hoje = LocalDate.now();
        LocalDate amanha = hoje.plusDays(1);

        // Uso de Streams para filtrar tarefas que vencem exatamente amanhã e não estão concluídas
        List<Tarefa> tarefasAmanha = tarefas.stream()
                .filter(t -> !t.isConcluida())
                .filter(t -> t.getDataVencimento().equals(amanha))
                .collect(Collectors.toList());

        if (!tarefasAmanha.isEmpty()) {
            System.out.println("\n" + Cores.AMARELO + "--- PAINEL DE ATENÇÃO ---" + Cores.RESET);

            tarefasAmanha.forEach(t -> {
                // Alteração solicitada: "ATENÇÃO" no lugar de "PRÓXIMA"
                System.out.println(Cores.VERMELHO + " [!] ATENÇÃO: " + Cores.RESET +
                        t.getTitulo() + Cores.AMARELO + " (Vence amanhã!)" + Cores.RESET);
            });

            System.out.println(Cores.AMARELO + "-------------------------" + Cores.RESET);
        }
    }
}