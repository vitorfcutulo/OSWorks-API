package com.algaworks.osworks.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.algaworks.osworks.domain.exception.NegocioException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrdemServico {
	
	 @Id
	 @EqualsAndHashCode.Include
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 @Valid
	 @NotNull
	 @ManyToOne
	 private Cliente cliente;
	 
	 @NotBlank
	 private String descricao;
	 
	 @NotNull
	 private BigDecimal preco;
	 
	 @Enumerated(EnumType.STRING)
	 @JsonProperty(access = Access.READ_ONLY)
	 private StatusOrdemServico status;
	 
	 @JsonProperty(access = Access.READ_ONLY)
	 private OffsetDateTime dataAbertura;
	 
	 @JsonProperty(access = Access.READ_ONLY)
	 private OffsetDateTime dataFinalizacao;
	 
	 @OneToMany(mappedBy = "ordemServico")
	 private List<Comentario> comentarios = new ArrayList<>();

	 public boolean podeSerFinalizada() {
		return StatusOrdemServico.ABERTA.equals(getStatus());
	 }
	 
	 public boolean naoPodeSerFinalizada() {
		return !podeSerFinalizada();
	 }
	 
	 public void finalizar() {
		 if (naoPodeSerFinalizada()) {
		   	throw new NegocioException("Ordem de serviço não pode ser finalizada");
		 }
		
		 setStatus(StatusOrdemServico.FINALIZADA);
		 setDataFinalizacao(OffsetDateTime.now());
	 }
}
