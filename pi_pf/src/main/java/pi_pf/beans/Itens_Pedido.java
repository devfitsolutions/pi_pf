package pi_pf.beans;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="tens_pedido")
public class Itens_Pedido {

	@Id
	@GeneratedValue
	@Column(name="ipe_id")
	private int id;
	
	@Column(name="ipe_qtde")
	private int quantidade;
	
	
	@Column(name="ipe_valorunit")
	private float valorUnitario;
	
	@Column(name="ipe_subtotal")
	private float subTotal;
	
	@OneToMany(mappedBy = "prod_id", cascade = CascadeType.ALL, fetch=FetchType.EAGER) //cascade significa que quando excluir um pessoa da tabela, todos os telefone também serão excluídos.
    private List<Produto> listaProdutos = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name="ped_id", nullable = false)
	private Pedido pedido;
	
//	@ManyToOne
//	@JoinColumn(name="prod_id", nullable = false)
//	private Produto produto;
	

	public Pedido getPedido() {
		return pedido;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public float getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(float valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}


	
	
}
