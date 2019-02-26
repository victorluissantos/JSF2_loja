package controller.mb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.richfaces.component.UIDataTable;

import controller.CarrinhoController;
import controller.ProdutoController;

import model.beans.ItemVenda;
import model.beans.Produto;
import model.beans.Venda;

@ManagedBean
@SessionScoped
public class CarrinhoMB {
	
	private Venda venda;
	private List<ItemVenda> itens = new ArrayList<ItemVenda>();
	private List<Produto> produtos;
	private BigDecimal total;
	private String formaPagto;
	
	
	private List<SelectItem> listaFormaPagto;
	private Produto produtoSelecionado;
	private UIDataTable dataTable;
	
	
	
	public CarrinhoMB(){
		produtos = new ProdutoController().listar();
		setTotal(new BigDecimal(0));
		listaFormaPagto = new ArrayList<SelectItem>();
		listaFormaPagto.add(new SelectItem("cartao_visa", "Cartão Visa"));
		listaFormaPagto.add(new SelectItem("cartao_master", "Cartão Master"));
		listaFormaPagto.add(new SelectItem("boleto", "Boleto"));
	}
	
	public String addItem(){
		ItemVenda iv = new ItemVenda();
		iv.setProduto(produtoSelecionado);
		iv.setQtde(new BigDecimal(1));
		iv.setTotal(iv.getProduto().getPreco().multiply(iv.getQtde()));
		itens.add(iv);
		calcularTotal();
		return "carrinho";
	}
	
	public void alterarQtde(ValueChangeEvent event){
		BigDecimal qtde = (BigDecimal) event.getNewValue();
		itens.get(dataTable.getRowIndex()).setQtde(qtde);
		itens.get(dataTable.getRowIndex()).setTotal(
				itens.get(dataTable.getRowIndex()).getQtde().multiply(itens.get(dataTable.getRowIndex()).getProduto().getPreco())
		);
		calcularTotal();
	}
	
	public void calcularTotal(){
		total = new BigDecimal(0);
		for (ItemVenda item : itens) {
			total = total.add(item.getTotal());
		}
	}
	
	public String removerItem(ActionEvent event){
		System.out.println(event.getPhaseId());
		System.out.println(dataTable.getRowIndex());
		ItemVenda iv = itens.remove(dataTable.getRowIndex());
		System.out.println(iv.getProduto().getDescricao());
		return "carrinho";
	}
	
	public String finalizarCompra(){
		venda = new Venda();
		venda.setItens(itens);
		venda.setData(new Date());
		venda.setTotal(total);
		return "finalizarCompra";
	}
	
	public String concluir(){
		for(ItemVenda item : itens){
			item.setVenda(venda);
		}
		venda.setFormaPagto(formaPagto);
		if(new CarrinhoController().concluirVenda(venda)){
			return "compraConcluida";
		}
		FacesMessage fm = new FacesMessage("Falha ao concluir compra");
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage("Falha", fm);
		return "";
	}
	
	public String inicioAposCompra(){
		venda = new Venda();
		itens = new ArrayList<ItemVenda>();
		total = new BigDecimal(0);
		formaPagto = null;
		return "index";
	}
	
	
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

	public UIDataTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(UIDataTable dataTable) {
		this.dataTable = dataTable;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<SelectItem> getListaFormaPagto() {
		return listaFormaPagto;
	}

	public void setListaFormaPagto(List<SelectItem> listaFormaPagto) {
		this.listaFormaPagto = listaFormaPagto;
	}

	public String getFormaPagto() {
		return formaPagto;
	}

	public void setFormaPagto(String formaPagto) {
		this.formaPagto = formaPagto;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
}