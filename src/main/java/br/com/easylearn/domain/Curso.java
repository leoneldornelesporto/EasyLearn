package br.com.easylearn.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Curso extends AbstractEntity{

	private String uuid = UUID.randomUUID().toString().replace("-","");
	private String nome;
	private String descricao;
	private Integer cargaHoraria;
	private String data = getCurrentTimeStamp();
	private String imagemIcon;
	@OneToOne
	private Categoria categoria;
	@OneToMany
	private List<Formacao> formacaoList;
	@OneToMany
	private List<Reforco> reforcoList;
	@OneToMany
	private List<Aluno> alunos;
	@OneToOne
	private Professor professor;
	@OneToMany
	private List<Tutor> tutores;
	@OneToMany
	private List<Modulo> moduloList;
	@OneToMany
	private List<Ocorrencia> ocorrenciaList;
	@OneToMany
	private List<Ministra> ministraList;
	@OneToMany
	private List<Matricula> matriculaList;

	public Curso() {
	}

	public Curso(Integer carga_horaria, String descricao, String nome, String imagemIcon, Professor professor,Categoria categoria) {
		this.cargaHoraria = carga_horaria;
		this.descricao = descricao;
		this.nome = nome;
		this.imagemIcon = imagemIcon;
		this.professor = professor;
		this.categoria = categoria;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getCargaHoraria() {
		return cargaHoraria;
	}

	public String getData() {
		return data;
	}

	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public String getImagemIcon() {
		return imagemIcon;
	}

	public void setImagemIcon(String imagemIcon) {
		this.imagemIcon = imagemIcon;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Formacao> getFormacaoList() {
		return formacaoList;
	}

	public void setFormacaoList(List<Formacao> formacaoList) {
		this.formacaoList = formacaoList;
	}

	public List<Reforco> getReforcoList() {
		return reforcoList;
	}

	public void setReforcoList(List<Reforco> reforcoList) {
		this.reforcoList = reforcoList;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Tutor> getTutores() {
		return tutores;
	}

	public void setTutores(List<Tutor> tutores) {
		this.tutores = tutores;
	}

	public List<Modulo> getModuloList() {
		return moduloList;
	}

	public void setModuloList(List<Modulo> moduloList) {
		this.moduloList = moduloList;
	}

	public List<Ocorrencia> getOcorrenciaList() {
		return ocorrenciaList;
	}

	public void setOcorrenciaList(List<Ocorrencia> ocorrenciaList) {
		this.ocorrenciaList = ocorrenciaList;
	}

	public List<Ministra> getMinistraList() {
		return ministraList;
	}

	public void setMinistraList(List<Ministra> ministraList) {
		this.ministraList = ministraList;
	}

	public List<Matricula> getMatriculaList() {
		return matriculaList;
	}

	public void setMatriculaList(List<Matricula> matriculaList) {
		this.matriculaList = matriculaList;
	}

	public String getCurrentTimeStamp() {
		return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	}
}
