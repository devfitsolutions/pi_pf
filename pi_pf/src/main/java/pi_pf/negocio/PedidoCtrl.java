package pi_pf.negocio;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pi_pf.beans.FormaPgto;
import pi_pf.beans.Itens_Pedido;
import pi_pf.beans.Pedido;
import pi_pf.beans.Produto;

@ManagedBean
@SessionScoped
public class PedidoCtrl implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pedido pedido = new Pedido();
	private Produto produto = new Produto();
	private Itens_Pedido itens = new Itens_Pedido();
	private FormaPgto formaPgto = new FormaPgto();
	private boolean desabilitarParcelas = true;

	// private UsuarioController uc = new UsuarioController();

	// private String usuario = uc.getPessoa().getEmail();


	public String adicionarProdutoAoCarrinho(Produto p) {
		// Adiciona produtos no carrinho
		this.pedido.getListaProdutos().add(p);
		
		return null;
	}

	public String actionPagamento() {
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
	
    public void valorDoPedido(){ // Varre a lista de produtos e soma todos os preços
		float valorTotal= 0;
		for(int i = 0; i < pedido.getListaProdutos().size(); i++){
			valorTotal += pedido.getListaProdutos().get(i).getPreco();
		}
		//this.itens.setSubTotal(valorTotal);
		this.pedido.setTotal(valorTotal);
	}
	
    public String calcQuantidadeProduto(Produto p){ // pega a quantidade de produtos que o cliente solicitou e o preço (subtotal)
		valorDoPedido();
		if(itens.getQuantidade() > 1){
			float subtotalAtualizado = this.itens.getSubTotal() - p.getPreco();
			int qtd = itens.getQuantidade();
			this.itens.setSubTotal(subtotalAtualizado + (p.getPreco() * qtd));
			this.pedido.setTotal(subtotalAtualizado + (p.getPreco() * qtd));
		}
		return null;
	}
	
	public String excluirProdutoDoCarrinho(Produto p ){
		for(int i = 0; i < this.pedido.getListaProdutos().size(); i++){
			if(this.pedido.getListaProdutos().get(i).getId() == p.getId()){
				this.pedido.getListaProdutos().remove(i);
				this.itens.setSubTotal(this.getItens().getSubTotal() - p.getPreco());
			}
		}
		return null;
	}
	
    public void setDesabilitarParcelas(boolean desabilitarParcelas) {
		this.desabilitarParcelas = desabilitarParcelas;
	}

	public String definirParcelas(){ // para saber se a opção de forma de pagamento é de cartão de crédito, boleto ou débito 
		
		if(this.formaPgto.getId() == 1){
			this.desabilitarParcelas = false;
			this.pedido.setTotal(this.itens.getSubTotal());
			this.pedido.setDesconto(0);
			
		}else if(this.formaPgto.getId() == 2){
			this.pedido.setDesconto((float)0.02);
			this.pedido.setDesconto(0);
			this.desabilitarParcelas = true;
			this.formaPgto.setNumPadraoParc(1);	
			
		} else{
			this.desabilitarParcelas = true;
			this.formaPgto.setNumPadraoParc(1);		
			this.pedido.setTotal(this.itens.getSubTotal());
			this.pedido.setDesconto(0);
		}
		return null;
	}
	
	public String jurosSobreParcela(){ 
		
			this.pedido.setTotal(this.itens.getSubTotal());
		return null;
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
	public Itens_Pedido getItens() {
		return itens;
	}

	public void setItens(Itens_Pedido itens) {
		this.itens = itens;
	}
}
