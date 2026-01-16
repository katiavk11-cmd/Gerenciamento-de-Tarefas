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
        this.status = Status.PENDENTE; // Padrão inicial
    }

    // Getters: Permitem que outras classes leiam os dados privados
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public LocalDate getDataEntrega() { return dataEntrega; }
    public Status getStatus() { return status; }

    // Setter: Permite alterar o status
    public void setStatus(Status status) { this.status = status; }

    // Lógica para verificar se o prazo expirou
    public boolean isVencida() {
        // Retorna verdadeiro se a data for antes de hoje E não estiver concluída
        return dataEntrega.isBefore(LocalDate.now()) && status != Status.CONCLUIDA;
    }

    @Override
    public String toString() {
        String alerta = isVencida() ? " [ALERTA: PRAZO VENCIDO!] " : "";
        return String.format("[%s]%s %s (Vence em: %s) - %s",
                status, alerta, titulo, dataEntrega.format(fmt), descricao);
    }
}