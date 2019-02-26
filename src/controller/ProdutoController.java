package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.criterion.Order;

import model.beans.Produto;
import model.dao.ProdutoDao;

public class ProdutoController {
	
	public List<Produto> listar(){
		List<Produto> lista = new ArrayList<Produto>();
		try {
			lista = new ProdutoDao().listar(Order.asc("descricao"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public boolean cadastrar(Produto prod){
		try {
			new ProdutoDao().salvar(prod);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Falha ao cadastrar produto"));
			return false;
		}
	}

}
