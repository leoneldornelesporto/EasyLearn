package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.Ocorrencia;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OcorrenciaDto {

    private UsuarioDto usuarioDto;
    private CursoDto cursoDto;
    private String descricao;
    private LocalDateTime date;

    public OcorrenciaDto(Ocorrencia ocorrencia) {
        this.usuarioDto = UsuarioDto.converter(ocorrencia.getUsuario());
        this.cursoDto = CursoDto.converter(ocorrencia.getCurso());
        this.descricao = ocorrencia.getDescricao();
        this.date = ocorrencia.getDate();
    }

    public static List<OcorrenciaDto> converter(List<Ocorrencia> allOcorrencia) {
        return allOcorrencia.stream().map(OcorrenciaDto::new).collect(Collectors.toList());
    }

    public UsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public CursoDto getCursoDto() {
        return cursoDto;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
