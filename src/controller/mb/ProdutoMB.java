package controller.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import controller.ProdutoController;

import model.beans.Produto;

@ManagedBean
@RequestScoped
public class ProdutoMB {
	
	private Produto prod = new Produto();
	
	public String cadastrar(){
		if(new ProdutoController().cadastrar(prod)){
			return "index";
		}
		return "";
	}
	
	
	
	public Produto getProd() {
		return prod;
	}

	public void setProd(Produto prod) {
		this.prod = prod;
	}

}
