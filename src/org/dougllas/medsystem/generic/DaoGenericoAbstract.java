package org.dougllas.medsystem.generic;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class DaoGenericoAbstract<T> implements DaoGenerico<T> {

	@PersistenceContext
	private EntityManager entityManager;

	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public DaoGenericoAbstract() {
		this.clazz = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public T insere(T bean) {
		entityManager.persist(bean);
		return bean;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void atualiza(T bean) {
		entityManager.merge(bean);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void remove(T bean) {
		bean = entityManager.merge(bean);
		entityManager.remove(bean);
	}

	@Override
	public T achar(Integer id) {
		return entityManager.find(getClazz(), id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T acharPesqParam(String queryS, Map<String, Object> params) {
		Query query = entityManager.createQuery(queryS);
		for (String key : params.keySet())
			query.setParameter(key, params.get(key));
		try {
			return (T) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> todos() {
		return entityManager.createQuery("from " + getClazz().getSimpleName())
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listPesqParam(String queryS, Map<String, Object> params) {
		Query query = entityManager.createQuery(queryS);
		for (String key : params.keySet())
			query.setParameter(key, params.get(key));
		return query.getResultList();
	}

	@Override
	public List<T> listPesqParam(String queryS, Map<String, Object> params,
			String ordem) {
		return null;
	}

	public Class<T> getClazz() {
		return clazz;
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<T> listByExample(T bean, Integer matchmode, Date dataInicial, Date dataFinal, String order) {
		return null;
	}
}
