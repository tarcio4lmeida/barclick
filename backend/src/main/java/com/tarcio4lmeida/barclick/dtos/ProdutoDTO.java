package com.tarcio4lmeida.barclick.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProdutoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@Size(min = 5, max = 60, message = "Deve ter entre 5 e 60 caracteres")
	@NotBlank(message = "Nome tem que ser preenchido")
	private String nome;
	
	@NotBlank(message = "Campo requerido")
	private String descricao;
	
	@Positive(message="Preço deve ser um valor positivo")
	private Double price;
	
	private String imgUrl;
	
	@PastOrPresent(message = "A data do produto nao pode ser futura")
	private Instant date;

	private List<CategoriaDTO> categorias = new ArrayList<>();

}
