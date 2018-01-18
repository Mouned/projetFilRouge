package fr.dta.projetFilRouge.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
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

	public void persist(Object entity) {
		em.persist(entity);
	}

	public List<T> getAll() {

		TypedQuery<T> query = em.createQuery("select t from " + klass.getName() + " t", klass);

		return query.getResultList();
	}

	public void createT(T t) {

		this.persist(t);
	}

	public T getEntityById(Long id) {

		TypedQuery<T> query = em.createQuery("select t from" + klass.getName() + " t where t.id=:id", klass);

		query.setParameter("id", id);

		return query.getSingleResult();
	}

	public void updateEntityById(T t) {

		em.merge(t);
	}

	public void deleteEntityById(Long id) {
		TypedQuery<T> query = em.createQuery("delete from " + klass.getName() + "  where id:=id", klass);

		query.setParameter("id", id);

	}

}
