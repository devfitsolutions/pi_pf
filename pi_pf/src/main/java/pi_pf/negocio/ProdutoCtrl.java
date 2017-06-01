package pi_pf.negocio;


import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pi_pf.persistencia.ProdutoDAO;
import pi_pf.beans.Produto;

@ManagedBean
@SessionScoped
public class ProdutoCtrl implements Serializable{

	private static final long serialVersionUID = 1L;
	private Produto produto;
	
	
	public List<Produto> getListagem(){
		return ProdutoDAO.listagem("");
	}
	
	public String actionGravar(){
		
		System.out.println("ID = " + produto.getId());
		System.out.println("Nome = " + produto.getNome());
		System.out.println("Preco = " + produto.getPreco());
		
		if(produto.getId() == 0){
			ProdutoDAO.inserir(produto);
			return actionInserir();
		}else{
			ProdutoDAO.alterar(produto);
			return "/produto/lista_produto";
		}		
	}
	
	public String actionInserir(){
		produto = new Produto();
		return "/produto/lista_produto";
	}
	
	public String actionExcluir(){
		ProdutoDAO.excluir(produto);
		return "/produto/lista_produto";
	}
	
		
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}


}
