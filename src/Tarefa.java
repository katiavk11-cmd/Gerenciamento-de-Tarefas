import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tarefa {
    private String titulo;
    private String descricao;
    private LocalDate dataEntrega;
    private Status status;

    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Tarefa(String titulo, String descricao, LocalDate dataEntrega) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataEntrega = dataEntrega;
        this.status = Status.PENDENTE;
    }

    // --- GETTERS E SETTERS ---
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public LocalDate getDataEntrega() { return dataEntrega; }
    public Status getStatus() { return status; }

    public void setStatus(Status status) {
        this.status = status;
    }

    // --- LÓGICA DE NEGÓCIO ---

    public boolean isVencida() {
        return dataEntrega.isBefore(LocalDate.now()) && !isConcluida();
    }

    /**
     * MELHORIA: Usa o método que criamos no Enum Status para centralizar a lógica.
     */
    public boolean isConcluida() {
        return this.status.isFinalizado();
    }

    /**
     * MELHORIA: Renomeado para seguir o padrão Java Time API e retornar LocalDate.
     */
    public LocalDate getDataVencimento() {
        return this.dataEntrega;
    }

    @Override
    public String toString() {
        // Agora o status já traz sua própria cor e nome formatado
        String alertaVencimento = isVencida() ? Cores.erro(" [!] VENCIDA ") : "";

        return String.format("%s %s %s - %s (Prazo: %s)",
                status, // O toString do Enum resolve a cor e o nome
                alertaVencimento,
                Cores.NEGRITO + titulo + Cores.RESET,
                descricao,
                dataEntrega.format(fmt));
    }
}