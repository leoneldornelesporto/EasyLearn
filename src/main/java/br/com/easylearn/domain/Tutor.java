package br.com.easylearn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("TUTOR")
public class Tutor extends Usuario{
    @JsonIgnore
    @OneToMany
    private List<Reforco> reforcoList;

    public Tutor() {
    }

    public Tutor(String nomeCompleto, String nomeNoCertificado, String usuarioNaUrl, String email, String senha, String biografia, String linkedin, String github, String twitter, String empresa, String cargo) {
        super(nomeCompleto,nomeNoCertificado,usuarioNaUrl,email,senha,biografia,linkedin,github,twitter,empresa,cargo);
    }

    public Tutor(String nomeCompleto, String nomeNoCertificado, String usuarioNaUrl, String email, String senha, String biografia, String linkedin, String github, String twitter, String empresa, String cargo, Boolean privacidadeDoPerfil) {
        super(nomeCompleto,nomeNoCertificado,usuarioNaUrl,email,senha,biografia,linkedin,github,twitter,empresa,cargo,privacidadeDoPerfil);
    }

    public List<Reforco> getReforcoList() {
        return reforcoList;
    }

    public void setReforcoList(List<Reforco> reforcoList) {
        this.reforcoList = reforcoList;
    }
}
