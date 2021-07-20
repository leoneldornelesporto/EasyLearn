package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.Ministra;

import java.util.List;
import java.util.stream.Collectors;

public class MinistraDto {

    private ProfessorDto professorDto;
    private CursoDto cursoDto;

    public MinistraDto(Ministra ministra) {
        this.professorDto = ProfessorDto.converter(ministra.getProfessor());
        this.cursoDto = CursoDto.converter(ministra.getCurso());
    }

    public static List<MinistraDto> converter(List<Ministra> allMinistra) {
        return allMinistra.stream().map(MinistraDto::new).collect(Collectors.toList());
    }

    public ProfessorDto getProfessorDto() {
        return professorDto;
    }

    public CursoDto getCursoDto() {
        return cursoDto;
    }
}
