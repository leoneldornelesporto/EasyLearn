package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Modulo;
import br.com.easylearn.repository.ModuloRepository;

public class AtualizacaoModuloForm {

    private Integer indice;
    private String titulo;
    private String tituloSecundario;

    public Modulo atualizar(Long idModulo, ModuloRepository moduloRepository) {
        Modulo modulo = moduloRepository.getOne(idModulo);
        modulo.setIndice(indice);
        modulo.setTitulo(titulo);
        modulo.setTituloSecundario(tituloSecundario);

        return modulo;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setTituloSecundario(String tituloSecundario) {
        this.tituloSecundario = tituloSecundario;
    }
}
