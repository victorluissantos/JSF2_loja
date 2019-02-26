package model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import util.HibernateUtil;

import model.beans.Produto;

public class ProdutoDao extends Dao<Produto> {
	

	public ProdutoDao() {
		super(Produto.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> listar(Order ordem) throws Exception{
		Session s = HibernateUtil.getSession();
		Criteria c = s.createCriteria(Produto.class);
		c.addOrder(ordem);
		return c.list();
	}

}