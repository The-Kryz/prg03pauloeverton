package br.com.ifba.curso.service;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.repository.CursoRepository;
import br.com.ifba.infrastructure.util.StringUtil;
import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j // Cria o log
public class CursoService implements CursoIService {

    // É OBRIGATÓRIO ser 'final' para o @RequiredArgsConstructor funcionar
    private final CursoRepository cursoRepository;

    @Override
    public Curso save(Curso curso) {
        log.info("Service: Tentando salvar curso: {}", curso.getNome());

        if (StringUtil.isNullOrEmpty(curso.getNome())) {
            log.error("Service: Falha! Nome vazio.");
            throw new RuntimeException("O nome do curso é obrigatório!");
        }

        if (StringUtil.isNullOrEmpty(curso.getCodigoCurso())) {
            log.error("Service: Falha! Código vazio.");
            throw new RuntimeException("O código do curso é obrigatório!");
        }

        return cursoRepository.save(curso);
    }

    @Override
    public Curso update(Curso curso) {
        log.info("Service: Atualizando curso ID {}", curso.getId());

        if (StringUtil.isNullOrEmpty(curso.getNome())) {
            log.warn("Service: Tentativa de update sem nome.");
            throw new RuntimeException("O nome do curso não pode ficar vazio!");
        }
        return cursoRepository.save(curso);
    }

    @Override
    public void delete(Curso curso) {
        log.info("Service: Deletando curso {}", curso.getNome());

        if (curso == null) {
            throw new RuntimeException("O curso para excluir não pode ser nulo.");
        }
        cursoRepository.delete(curso);
    }

    @Override
    public List<Curso> findAll() {
        log.info("Service: Listando todos os cursos.");
        return cursoRepository.findAll();
    }

    @Override
    public Curso findByCodigoCurso(String codigoCurso) {
        log.info("Service: Buscando código {}", codigoCurso);
        return cursoRepository.findByCodigoCurso(codigoCurso);
    }
}
