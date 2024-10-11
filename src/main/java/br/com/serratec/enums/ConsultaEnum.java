package br.com.serratec.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.serratec.exception.EnumException;

public enum ConsultaEnum {
	AGUARDANDO, ATENDIMENTO, ATENDIDO;
	
	@JsonCreator
	public static ConsultaEnum verificar(String valor) {
		for(ConsultaEnum c: ConsultaEnum.values()) {
			if (c.name().equals(valor)) {
				return c;
			}
		}
		throw new EnumException("Estado de consulta invalida.");
	}
}
