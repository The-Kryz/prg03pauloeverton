package br.com.ifba;

// Importações necessárias para o Spring funcionar e para acharmos sua tela
import br.com.ifba.curso.view.CursoListar;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Classe Principal (Main). Analogia: É o motor de arranque. É aqui que a mágica
 * começa.
 */
@SpringBootApplication // <--- A "ETIQUETA MÁGICA"
// Essa anotação é o comando supremo. Ela diz ao Java: 
// "Isso é um projeto Spring Boot. Varra todas as pastas, 
// ache os @Controller, @Service, @Repository e configure tudo sozinho."
public class Prg03Application {

    public static void main(String[] args) {

        // --- BLOCO 1: PREPARANDO O TERRENO PARA DESKTOP ---
        // O Spring Boot nasce pensando que é um Servidor Web (que não tem monitor).
        // Se rodarmos direto, ele tenta rodar em modo "Headless" (Sem cabeça/Sem monitor).
        // Como seu projeto é Swing (Janelas), precisamos avisar: "Eu tenho monitor!".
        ConfigurableApplicationContext context
                = new SpringApplicationBuilder(Prg03Application.class)
                        .headless(false) // <--- IMPORTANTE: "Não rode sem cabeça! Eu tenho tela."
                        .run(args);      // <--- Aqui o Spring liga, conecta no banco e cria todos os objetos.

        // --- BLOCO 2: PEGANDO A TELA "TUNADA" ---
        // AQUI ESTÁ O PULO DO GATO!
        // Se fizéssemos: CursoListar tela = new CursoListar(); -> ERRO!
        // Por quê? Porque se VOCÊ der o 'new', a tela nasce "burra", sem conexão com o Spring.
        // O jeito certo: Pedimos pro Spring (context) nos dar a tela que ELE criou.
        // O método .getBean() vai no "almoxarifado" do Spring e pega a tela já com tudo injetado (@Autowired).
        CursoListar telaPrincipal = context.getBean(CursoListar.class);

        // Agora que temos a tela gerenciada pelo Spring, podemos mostrá-la.
        telaPrincipal.setVisible(true);
    }
}
