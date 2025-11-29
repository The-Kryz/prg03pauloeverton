package br.com.ifba.curso.controller;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.service.CursoService;
import java.util.List;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CursoController implements CursoIController {

    private final CursoService cursoService;

    @Override
    public List<Curso> findAll() {
        log.info("Controller: Solicitando lista de todos os cursos.");
        return cursoService.findAll();
    }

    @Override
    public Curso save(Curso curso) {
        log.info("Controller: Recebendo pedido para salvar curso: {}", curso.getNome());
        return cursoService.save(curso);
    }

    @Override
    public Curso update(Curso curso) {
        log.info("Controller: Recebendo pedido para atualizar curso ID: {}", curso.getId());
        return cursoService.update(curso);
    }

    @Override
    public void delete(Curso curso) {
        log.info("Controller: Recebendo pedido para deletar curso.");
        cursoService.delete(curso);
    }

    @Override
    public Curso findByCodigoCurso(String codigoCurso) {
        log.info("Controller: Buscando curso por código: {}", codigoCurso);
        return cursoService.findByCodigoCurso(codigoCurso);
    }
}
