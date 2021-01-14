package com.algaworks.osworks.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	 
	 @ManyToOne
	 private Cliente cliente;
	 
	 private String descricao;
	 private BigDecimal preco;
	 
	 @Enumerated(EnumType.STRING)
	 @JsonProperty(access = Access.READ_ONLY)
	 private StatusOrdemServico status;
	 
	 @JsonProperty(access = Access.READ_ONLY)
	 private LocalDateTime dataAbertura;
	 
	 @JsonProperty(access = Access.READ_ONLY)
	 private LocalDateTime dataFinalizacao;
}
