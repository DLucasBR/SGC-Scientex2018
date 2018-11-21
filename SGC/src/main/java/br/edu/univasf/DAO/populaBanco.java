package br.edu.univasf.DAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.edu.univasf.model.Aluno;
import br.edu.univasf.model.Curso;
import br.edu.univasf.model.Instrutor;
import br.edu.univasf.model.enums.AreasDoConhecimento;
import br.edu.univasf.model.enums.EstadosFederacao;
import br.edu.univasf.model.enums.Salas;

public class populaBanco {
	

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("CursoUP");
		EntityManager manager = factory.createEntityManager();

		EntityTransaction trx = manager.getTransaction();
		trx.begin();

		Aluno aluno = new Aluno();
		aluno.setNome("Daniel Lucas");
		aluno.setBairro("maringa");
		aluno.setCep("48902-907");
		aluno.setCPF("058.758.134-40");
		aluno.setEmail("dalucasdna@hotmail.com");
		aluno.setMunicipio("Juazeiro");
		aluno.setNumeroEndereco("357");
		aluno.setProfissao("Engenheiro");
		aluno.setUF(EstadosFederacao.BA);
		aluno.setCursos(new ArrayList<>());
		aluno.setLogradouro("Rua Jose saraiva Correia, 100");

		manager.persist(aluno);
		
		Instrutor instrutor = new Instrutor();
		instrutor.setNome("Ana Emilia");
		instrutor.setEmail("ana@email.com");
		instrutor.setProfissao("Professora");
		instrutor.setTelefone("87991279126");
		instrutor.setAreaDoConhecimento(AreasDoConhecimento.Computacao);
		
		manager.persist(instrutor);
		
		Curso curso = new Curso();
		curso.setDescricao("Curso de JSF com java");
		curso.setJustificativa("Nenhuma, so queria mesmo");
		curso.setData(LocalDate.now());
		curso.setSala(Salas.SalaCinco);
		curso.setInstrutor(instrutor);
		curso.setTitulo("Curso de JSF");
		curso.setAreaDoConhecimento(AreasDoConhecimento.Computacao);
		
		
		List<Aluno> alunos = new ArrayList<>();
		alunos.add(aluno);
		curso.setAlunos(alunos);

		manager.persist(curso);
		
		trx.commit();
	}

}
