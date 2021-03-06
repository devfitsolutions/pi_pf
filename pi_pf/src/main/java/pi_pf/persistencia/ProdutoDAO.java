package pi_pf.persistencia;


import java.io.Serializable;
import java.util.List;

import org.hibernate.*;

import pi_pf.beans.Produto;

public class ProdutoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static void inserir(Produto produto) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.save(produto);
		t.commit();
		sessao.close();
	}

	public static void alterar(Produto produto) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.update(produto);
		t.commit();
		sessao.close();
	}

	public static void excluir(Produto produto) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.delete(produto);
		t.commit();
		sessao.close();
	}

	
	public static List<Produto> listagem(String filtro) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Query consulta;
		if (filtro.trim().length() == 0) {
			consulta = sessao.createQuery("from Produto order by prod_nome ");
		} else {
			consulta = sessao.createQuery("from Produto  "
					+ "where prod_nome like :parametro order by prod_nome ");
			consulta.setString("parametro", "%" + filtro + "%");
		}
		List lista = consulta.list();
		sessao.close();
		return lista;
	}
			

}
