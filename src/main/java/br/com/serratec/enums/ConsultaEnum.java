package br.com.serratec.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.serratec.exception.EnumException;

public enum ConsultaEnum {
	AGUARDANDO(1, "AGUARDANDO"), 
	ATENDIMENTO(2, "ATENDIMENTO"), 
	ATENDIDO(3, "ATENDIDO");
	
	private Integer codigo;
	private String tipo;
	
	private ConsultaEnum(Integer codigo, String tipo) {
		this.codigo = codigo;
		this.tipo = tipo;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@JsonCreator
	public static ConsultaEnum verificar(Integer valor) {
		for(ConsultaEnum c: ConsultaEnum.values()) {
			if (c.getCodigo().equals(valor)) {
				return c;
			}
		}
		throw new EnumException("Status Invalido");
	}

}
