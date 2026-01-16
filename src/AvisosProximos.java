import java.util.List;
import java.time.LocalDate;

public class AvisosProximos {

    public void verificarVencimentos(List<Tarefa> lista) {
        LocalDate hoje = LocalDate.now();
        LocalDate amanha = hoje.plusDays(1);
        boolean temAviso = false;

        System.out.println("\n>>> [RESUMO DE ALERTAS]");

        for (Tarefa t : lista) {
            // 1. Verifica se já venceu e não está concluída
            if (t.isVencida()) {
                System.out.println(" [!] VENCIDA: " + t.getTitulo() + " (Prazo: " + t.getDataEntrega() + ")");
                temAviso = true;
            }
            // 2. Verifica se vence amanhã e não está concluída
            else if (t.getDataEntrega().equals(amanha) && t.getStatus() != Status.CONCLUIDA) {
                System.out.println(" [>] AMANHÃ: " + t.getTitulo());
                temAviso = true;
            }
        } // Fim do for

        if (!temAviso) {
            System.out.println(" [v] Tudo em dia! Nenhuma tarefa atrasada ou para amanhã.");
        }

        System.out.println("------------------------------------------");
    } // Fim do método
} // Fim da classe - ESTA CHAVE RESOLVE O ERRO 'REACHED END OF FILE'