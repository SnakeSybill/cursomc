package com.igorgrs.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.igorgrs.cursomc.services.validation.ClienteInsert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ClienteInsert
public class ClienteNewDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "E-mail inválido")
	private String email;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String cpfOuCnpj;
	
	private Integer tipo;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String logradouro;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String numero;
	
	private String complemento;
	
	private String bairro;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String cep;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String telefone1;
	private String telefone2;
	private String telefone3;

	private Integer cidadeId;
}
