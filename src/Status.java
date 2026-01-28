public enum Status {
    PENDENTE("Pendente", Cores.AMARELO),
    EM_ANDAMENTO("Em Andamento", Cores.AZUL),
    CONCLUIDA("Concluída", Cores.VERDE);

    private final String descricao;
    private final String cor;

    // Construtor do Enum para associar metadados
    Status(String descricao, String cor) {
        this.descricao = descricao;
        this.cor = cor;
    }

    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna o status formatado com a cor correspondente para o console.
     */
    @Override
    public String toString() {
        return cor + descricao + Cores.RESET;
    }

    /**
     * Facilita a lógica de verificação (usado no AvisosProximos e FiltrarTarefas).
     */
    public boolean isFinalizado() {
        return this == CONCLUIDA;
    }
}