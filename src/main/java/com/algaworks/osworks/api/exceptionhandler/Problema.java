package com.algaworks.osworks.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class Problema {
	
	private Integer status;
	private OffsetDateTime dataHora;
	private String titulo;
	private List<Campo> campos;
	
	@Data
	public static class Campo {
		
		private String nome;
		private String mensagem;
		
		public Campo(String nome, String mensagem) {
			super();
			this.nome = nome;
			this.mensagem = mensagem;
		}
		
	}

}
