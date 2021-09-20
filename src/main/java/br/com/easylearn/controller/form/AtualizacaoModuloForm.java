package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Aula;
import br.com.easylearn.domain.Modulo;
import br.com.easylearn.repository.AulaRepository;
import br.com.easylearn.repository.ModuloRepository;

import java.util.ArrayList;
import java.util.List;

public class AtualizacaoModuloForm {

    private Integer indice;
    private String titulo;
    private Long idAula;

    public Modulo atualizar(Long idModulo, ModuloRepository moduloRepository, AulaRepository aulaRepository) {
        List<Aula> aulaList = new ArrayList<>();
        Modulo modulo = moduloRepository.getOne(idModulo);
        modulo.setIndice(indice);
        modulo.setTitulo(titulo);

        Aula aula = aulaRepository.getOne(idAula);
        aulaList.add(aula);
        modulo.setAulaList(aulaList);
        return modulo;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setIdAula(Long idAula) {
        this.idAula = idAula;
    }
}
