package com.sfauser.cadastroVacinacao.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Required;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
@Table(name = "tb_aplicacao_vacina")
public class AplicacaoVacina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String nome;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "GMT")
	private Date data;

	@OneToOne
	@JoinColumn(name="email_paciente", referencedColumnName="email") 
	private Paciente paciente;

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public AplicacaoVacina() {
	}

	public AplicacaoVacina(Long id, String nomeVacina, Date dataVacina, Paciente paciente) {
		super();
		this.id = id;
		this.nome = nomeVacina;
		this.data = dataVacina;
		this.paciente = paciente;
	}

	public Long getId() {
		return id;
	}

	public String getNomeVacina() {
		return nome;
	}

	public void setNomeVacina(String nomeVacina) {
		this.nome = nomeVacina;
	}

	public Date getDataVacina() {
		return data;
	}

	public void setDataVacina(Date dataVacina) {
		this.data = dataVacina;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((paciente == null) ? 0 : paciente.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AplicacaoVacina other = (AplicacaoVacina) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}