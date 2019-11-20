package com.igorgrs.cursomc.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@JsonIgnore
	private ItemPedidoPK id;
	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	@JsonIgnore
	public Pedido getPedido() {
		return id.getPedido();
	}

	@JsonIgnore
	public Produto getProduto() {
		return id.getProduto();
	}

	public void setProduto(Produto prod) {
		id.setProduto(prod);
	}
	
	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
		super();
		this.id = new ItemPedidoPK();
		this.id.setPedido(pedido);
		this.id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public ItemPedido() {
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public double getSubTotal() {
		return preco * quantidade;
	}
	
	public void setPedido(Pedido pedido) {
		this.id.setPedido(pedido);
	}

}
