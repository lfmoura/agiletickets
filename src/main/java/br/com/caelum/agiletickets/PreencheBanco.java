package br.com.caelum.agiletickets;

import javax.persistence.EntityManager;

import org.joda.time.DateTime;

import br.com.caelum.agiletickets.models.Espetaculo;
import br.com.caelum.agiletickets.models.Estabelecimento;
import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;
import br.com.caelum.vraptor.util.jpa.EntityManagerCreator;
import br.com.caelum.vraptor.util.jpa.EntityManagerFactoryCreator;

public class PreencheBanco {

	public static void main(String[] args) {
		EntityManager manager = createEntityManager();

		limpaBanco(manager);
		Estabelecimento estabelecimento = criaEstabelecimento(manager);		
		Espetaculo espetaculo = criaEspetaculo(manager, estabelecimento);
		criaSessoes(manager, espetaculo);
		manager.getTransaction().commit();
		manager.close();
	}

	private static void criaSessoes(EntityManager manager, Espetaculo espetaculo) {
		for (int i = 0; i < 10; i++) {
			Sessao sessao = new Sessao();
			sessao.setEspetaculo(espetaculo);
			sessao.setInicio(new DateTime().plusDays(7+i));
			sessao.setDuracaoEmMinutos(60 * 3);
			sessao.setTotalIngressos(10);
			sessao.setIngressosReservados(10 - i);
			manager.persist(sessao);
		}
	}

	private static Espetaculo criaEspetaculo(EntityManager manager,
			Estabelecimento estabelecimento) {
		Espetaculo espetaculo = new Espetaculo();
		espetaculo.setEstabelecimento(estabelecimento);
		espetaculo.setNome("O mundo maravilhoso dos unicórnios e arco-íris");
		espetaculo.setTipo(TipoDeEspetaculo.BALLET);		
		manager.persist(espetaculo);
		return espetaculo;
	}

	private static Estabelecimento criaEstabelecimento(EntityManager manager) {
		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setNome("Casa da mãe Joana");
		estabelecimento.setEndereco("Rua da vovó. Casa ao lado.");
		manager.persist(estabelecimento);
		return estabelecimento;
	}

	private static void limpaBanco(EntityManager manager) {
		manager.getTransaction().begin();
		manager.createQuery("delete from Sessao").executeUpdate();
		manager.createQuery("delete from Espetaculo").executeUpdate();
		manager.createQuery("delete from Estabelecimento").executeUpdate();
	}

	private static EntityManager createEntityManager() {
		EntityManagerFactoryCreator creator = createCreater();
		EntityManagerCreator managerCreator = createManagerCreator(creator);
		EntityManager manager = managerCreator.getInstance();
		return manager;
	}

	private static EntityManagerCreator createManagerCreator(
			EntityManagerFactoryCreator creator) {
		EntityManagerCreator managerCreator = new EntityManagerCreator(creator.getInstance());
		managerCreator.create();
		return managerCreator;
	}

	private static EntityManagerFactoryCreator createCreater() {
		EntityManagerFactoryCreator creator = new EntityManagerFactoryCreator();
		creator.create();
		return creator;
	}
}
