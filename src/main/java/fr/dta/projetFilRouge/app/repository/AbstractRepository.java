package fr.dta.projetFilRouge.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
//import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

//@Transactional
public abstract class AbstractRepository<T /*extends AbstractEntity*/> {

	@PersistenceContext
	EntityManager em;

	private Class<T> klass;

	public AbstractRepository(Class<T> klass) {
		this.klass = klass;
	}

	public AbstractRepository() {

	}

	protected Session getSession() {
		return em.unwrap(Session.class);
	}
}
