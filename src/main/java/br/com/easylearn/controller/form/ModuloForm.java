package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Modulo;
import br.com.easylearn.repository.ModuloRepository;

public class ModuloForm {

    private Integer indice;
    private String titulo;
    private String tituloSecundario;

    public Modulo save(ModuloRepository moduloRepository) {
        Modulo modulo = new Modulo(indice,titulo,tituloSecundario);
        return moduloRepository.save(modulo);
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
