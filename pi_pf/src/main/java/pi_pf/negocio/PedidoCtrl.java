package pi_pf.negocio;


import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pi_pf.beans.FormaPgto;
import pi_pf.beans.Pedido;
import pi_pf.beans.Produto;


@ManagedBean
@SessionScoped
public class PedidoCtrl implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pedido pedido = new Pedido();
	private Produto produto = new Produto();
	//private Itens_Pedido itens = new Itens_Pedido();
	private FormaPgto formaPgto = new FormaPgto();
	private boolean desabilitarParcelas = true;
	
	// private UsuarioController uc = new UsuarioController();

	// private String usuario = uc.getPessoa().getEmail();

	public String adicionarProdutoAoCarrinho() { //Adiciona produtos no carrinho
		this.pedido.getListaProdutos().add(this.produto);
		
		return null;
	}
	
	public String actionPagamento(){
		return "/cliente/forma_de_pagamento?faces-redirect=true";
	}

	public Date dataDoSistema() { // Pega a data atual
		Date hoje = new Date();
		return hoje;
	}
	
	public Pedido getPedido() {
		return pedido;
	}

	public boolean isDesabilitarParcelas() {
		return desabilitarParcelas;
	}

	public void setDesabilitarParcelas(boolean desabilitarParcelas) {
		this.desabilitarParcelas = desabilitarParcelas;
	}

	public FormaPgto getFormaPgto() {
		return formaPgto;
	}

	public void setFormaPgto(FormaPgto formaPgto) {
		this.formaPgto = formaPgto;
	}


	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	
	

	
	

}

