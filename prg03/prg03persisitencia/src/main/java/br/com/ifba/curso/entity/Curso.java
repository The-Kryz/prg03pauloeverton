package br.com.ifba.curso.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor; 
import lombok.Data;              
import lombok.EqualsAndHashCode; 
import lombok.NoArgsConstructor; 

@Entity
@Table(name = "curso")
// --- ANOTAÇÕES DO LOMBOK ---
@Data                  // Gera Getters, Setters, ToString, etc.
@AllArgsConstructor    // Gera construtor com tudo
@NoArgsConstructor     // Gera construtor vazio (obrigatório pro JPA)
@EqualsAndHashCode(callSuper = false)
public class Curso extends PersistenceEntity implements Serializable {

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "codigo_curso", nullable = false, unique = true)
    private String codigoCurso;

    @Column(name = "ativo")
    private boolean ativo;

}
