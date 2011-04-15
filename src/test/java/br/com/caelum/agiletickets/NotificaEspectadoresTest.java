package br.com.caelum.agiletickets;

import static org.mockito.Mockito.mock;

import java.util.Arrays;

import org.junit.Test;
import org.mockito.Mockito;

import br.com.caelum.agiletickets.models.Espectador;
import br.com.caelum.agiletickets.services.Enviador;
import br.com.caelum.agiletickets.services.NE;

public class NotificaEspectadoresTest {
	@Test
	public void deveMontarEmailParaQuemTemIngressoDaki2d(){
		Espectador e = new Espectador();
		Enviador carteiro = mock(Enviador.class);
		NE notificador = new NE(carteiro);
		notificador.notifica(Arrays.asList(e));
		Mockito.verify(carteiro).envia(e);
	}
}
