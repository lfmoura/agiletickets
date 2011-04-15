package br.com.caelum.agiletickets.services;

import java.util.List;

import br.com.caelum.agiletickets.models.Espectador;

public class NE {
	Enviador carteiro;
	
	public NE(Enviador e){
		this.carteiro = e;
	}
	
	public void notifica(List<Espectador> es){
		for (Espectador espectador : es) {
			carteiro.envia(espectador);
		}
	}

}
