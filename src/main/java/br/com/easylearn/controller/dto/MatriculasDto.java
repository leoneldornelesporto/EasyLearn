package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.Matricula;

import java.util.List;
import java.util.stream.Collectors;

public class MatriculasDto {

    private AlunoDto alunoDto;
    private CursoDto cursoDto;
    private Integer progresso;
    private String estado;
    private Boolean cursoConcluido;

    public MatriculasDto(Matricula matricula) {
        this.alunoDto = AlunoDto.converter(matricula.getAluno());
        this.cursoDto = CursoDto.converter(matricula.getCurso());
        this.progresso = matricula.getProgresso();
        this.estado = matricula.getEstado();
        this.cursoConcluido = matricula.getCursoConcluido();
    }

    public static List<MatriculasDto> converter(List<Matricula> allMatricula) {
        return allMatricula.stream().map(MatriculasDto::new).collect(Collectors.toList());
    }

    public AlunoDto getAlunoDto() {
        return alunoDto;
    }

    public CursoDto getCursoDto() {
        return cursoDto;
    }

    public Integer getProgresso() {
        return progresso;
    }

    public String getEstado() {
        return estado;
    }

    public Boolean getCursoConcluido() {
        return cursoConcluido;
    }
}
