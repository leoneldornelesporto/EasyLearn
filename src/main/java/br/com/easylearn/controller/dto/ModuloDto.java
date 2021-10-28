package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.Modulo;
import br.com.easylearn.repository.AulaRepository;

import java.util.ArrayList;
import java.util.List;

public class ModuloDto {

    private Long id;
    private Integer indice;
    private String titulo;
    private String tituloSecundario;
    private String nomeCurso;
    private List<AulaDto> aulaDto;

    public ModuloDto(Modulo modulo) {
        this.id = modulo.getId();
        this.indice = modulo.getIndice();
        this.titulo = modulo.getTitulo();
        this.tituloSecundario = modulo.getTituloSecundario();
        this.nomeCurso = modulo.getCurso().getNome();
    }

    public ModuloDto(Modulo modulo, AulaRepository aulaRepository) {
        this.id = modulo.getId();
        this.indice = modulo.getIndice();
        this.titulo = modulo.getTitulo();
        this.tituloSecundario = modulo.getTituloSecundario();
        this.nomeCurso = modulo.getCurso().getNome();
        this.aulaDto = AulaDto.converter(aulaRepository.findByModuloId(id));
    }

    public static ModuloDto converter(Modulo modulo, AulaRepository aulaRepository) {
        return new ModuloDto(modulo,aulaRepository);
    }

    public static List<ModuloDto> converter(List<Modulo> allModulos,AulaRepository aulaRepository) {
        List<ModuloDto> moduloDtoList = new ArrayList<>();

        for (Modulo modulo:allModulos){
            ModuloDto moduloDto = new ModuloDto(modulo,aulaRepository);
            moduloDtoList.add(moduloDto);
        }
        return moduloDtoList;
    }

    public Long getId() {
        return id;
    }

    public Integer getIndice() {
        return indice;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTituloSecundario() {
        return tituloSecundario;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public List<AulaDto> getAulaDto() {
        return aulaDto;
    }
}
