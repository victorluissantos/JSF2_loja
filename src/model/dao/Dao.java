package model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import util.HibernateUtil;

public abstract class Dao<T> {
	
	private Class<T> classe;
	
	public Dao(Class<T> classe){
		this.classe = classe;
	}
	
	public void salvar(T obj) throws Exception{
		Session s = HibernateUtil.getSession();
		s.beginTransaction().begin();
		s.save(obj);
		s.beginTransaction().commit();
		s.flush();
		s.clear();
		s.close();
	}
	
	public void atualizar(T obj) throws Exception{
		Session s = HibernateUtil.getSession();
		s.beginTransaction().begin();
		s.update(obj);
		s.beginTransaction().commit();
		s.flush();
		s.clear();
		s.close();
	}
	
	public void excluir(T obj)throws Exception{
		Session s = HibernateUtil.getSession();
		s.beginTransaction().begin();
		s.delete(obj);
		s.beginTransaction().commit();
		s.flush();
		s.clear();
		s.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listar()throws Exception{
		Session s = HibernateUtil.getSession();
		Criteria c = s.createCriteria(classe);
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		s.close();
		return c.list();
	}

}
