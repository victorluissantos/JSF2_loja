package controller;

import model.beans.Venda;
import model.dao.VendaDao;

public class CarrinhoController {
	
	public boolean concluirVenda(Venda venda){
		try {
			new VendaDao().salvar(venda);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
