package br.com.easylearn.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment extends AbstractEntity{
    private String idPayment;
    private String status;
    private String uuidCurso;
    private Long idAluno;
}
