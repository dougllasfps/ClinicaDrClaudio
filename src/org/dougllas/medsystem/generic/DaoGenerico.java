package org.dougllas.medsystem.generic;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DaoGenerico<T> {
	T insere(T bean);
	void atualiza(T bean);
	void remove(T bean);
	T achar(Integer id);
	T acharPesqParam(String queryS, Map<String, Object> params);
	List<T> todos();
	List<T> listPesqParam(String queryS, Map<String, Object> params);
	List<T> listPesqParam(String queryS, Map<String, Object> params, String ordem);
	List<T> listByExample(T bean, Integer matchmode, Date dataInicial, Date dataFinal, String order);
}
