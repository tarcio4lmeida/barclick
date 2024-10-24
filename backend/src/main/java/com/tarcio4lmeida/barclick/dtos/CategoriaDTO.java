package com.tarcio4lmeida.barclick.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoriaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long id;

	@NotBlank(message = "Nome tem que ser preenchido")
	private String nome;

}
