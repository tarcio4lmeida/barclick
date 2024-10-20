package com.tarcio4lmeida.barclick.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoriaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;

}
