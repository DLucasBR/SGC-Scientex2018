package br.edu.univasf.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import br.edu.univasf.model.Curso;
import br.edu.univasf.model.enums.AreasDoConhecimento;
import br.edu.univasf.model.enums.Salas;

public class DAO<T> {
	
	/*PS. Essa implementacao eh muito ineficiente, avisar aos alunos sobre isso
	 * comentar sobre usar CDI para gerenciar a injecao de dependencia de DAOS*/
	
	/*Lembrar de criar a base senao a inicilizacao do sistema lanca exception*/

	private final Class<T> classe;

	public DAO(Class<T> classe) {
		this.classe = classe;
	}

	public void adiciona(T t) {

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
	}

	public void remove(T t) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		em.remove(em.merge(t));

		em.getTransaction().commit();
		em.close();
	}

	public void atualiza(T t) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		em.merge(t);

		em.getTransaction().commit();
		em.close();
	}

	public List<T> listaTodos() {

		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).getResultList();

		em.close();
		return lista;
	}

	public T buscaPorId(Long id) {

		EntityManager em = new JPAUtil().getEntityManager();
		T instancia = em.find(classe, id);
		em.close();
		return instancia;

	}

	public int contaAreasDoConhecimentoNosCursos(AreasDoConhecimento area) {
		EntityManager em = new JPAUtil().getEntityManager();
		
		int resultado = em.createQuery("from Curso where areaDoConhecimento like :areaDoConhecimento", Curso.class)
				.setParameter("areaDoConhecimento", area).getResultList().size();
		
		em.close();
		return resultado;
	}
	
	public int contaSalasNosCursos(Salas sala) {
		EntityManager em = new JPAUtil().getEntityManager();
		
		int resultado = em.createQuery("from Curso where sala like :sala", Curso.class)
				.setParameter("sala", sala).getResultList().size();
		
		em.close();
		return resultado;
	}
}