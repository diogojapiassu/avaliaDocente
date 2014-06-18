package br.ufg.inf.es.mb;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class TesteMB {

	private String value = "Teste projeto com Primefaces";

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}