package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.Professor;
import br.com.easylearn.domain.Reforco;

import java.util.List;
import java.util.stream.Collectors;

public class ReforcoDto {

    private TutorDto tutorDto;
    private CursoDto cursoDto;

    public ReforcoDto(Reforco reforco) {
        this.tutorDto = TutorDto.converter(reforco.getTutor());
        this.cursoDto = CursoDto.converter(reforco.getCurso());
    }

    public static List<ReforcoDto> converter(List<Reforco> allReforcos) {
        return allReforcos.stream().map(ReforcoDto::new).collect(Collectors.toList());
    }

    public static ProfessorDto converter(Professor professor) {
        return new ProfessorDto(professor);
    }

    public TutorDto getTutorDto() {
        return tutorDto;
    }

    public CursoDto getCursoDto() {
        return cursoDto;
    }
}
