package br.com.ifba.infrastructure.util;

public class StringUtil {

    // Padrão Singleton (opcional, mas aqui faremos estático para facilitar)
    /**
     * Verifica se uma String é nula ou vazia.
     *
     * @param texto O texto a ser validado.
     * @return true se for nula ou vazia, false se tiver conteúdo.
     */
    public static boolean isNullOrEmpty(String texto) {
        return texto == null || texto.trim().isEmpty();
    }
}
