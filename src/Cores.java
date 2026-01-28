public final class Cores {
    // 1. Construtor privado impede que alguém instancie "new Cores()"
    private Cores() {
        throw new UnsupportedOperationException("Esta é uma classe utilitária e não pode ser instanciada.");
    }

    // Códigos ANSI para as cores
    public static final String RESET = "\u001B[0m";
    public static final String VERMELHO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARELO = "\u001B[33m";
    public static final String AZUL = "\u001B[34m";
    public static final String NEGRITO = "\u001B[1m";

    // 2. Uso de String.format para maior clareza nos métodos facilitadores
    public static String erro(String texto) {
        return String.format("%s%s%s", VERMELHO, texto, RESET);
    }

    public static String sucesso(String texto) {
        return String.format("%s%s%s", VERDE, texto, RESET);
    }

    public static String alerta(String texto) {
        return String.format("%s%s%s", AMARELO, texto, RESET);
    }

    public static String destaque(String texto) {
        return String.format("%s%s%s", NEGRITO, texto, RESET);
    }
}