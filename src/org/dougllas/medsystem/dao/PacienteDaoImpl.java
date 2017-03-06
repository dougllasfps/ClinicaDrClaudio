package org.dougllas.medsystem.dao;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.dougllas.medsystem.generic.DaoGenericoAbstract;
import org.dougllas.medsystem.model.Paciente;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("pacienteDao")
public class PacienteDaoImpl extends DaoGenericoAbstract<Paciente> implements
		PacienteDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Paciente> listByExample(Paciente bean, Integer matchmode, Date dataInicial, Date dataFinal, String order){
	
		Criteria pesq = ((Session) getEntityManager().getDelegate())
				.createCriteria(Paciente.class);
		
		pesq.addOrder(Order.asc(order));
		
		if(bean.getNumPaciente()!=null && bean.getNumPaciente()!=0){
			pesq.add(Restrictions.eq("numPaciente", bean.getNumPaciente()));
		}
		
		if(bean.getNome()!=null  &&  !bean.getNome().trim().equals("")){
			if(matchmode == 0)
				pesq.add(Restrictions.ilike("nome", bean.getNome(), MatchMode.ANYWHERE));
			if(matchmode==1)
				pesq.add(Restrictions.ilike("nome", bean.getNome(), MatchMode.START));
		}
		
		if(bean.getConvenio()!=null  &&  !bean.getConvenio().trim().equals("")){
			pesq.add(Restrictions.like("convenio", bean.getConvenio(),MatchMode.START));
		}
		
		if(dataInicial!=null && dataFinal==null){
			pesq.add(Restrictions.between("dataCadastro", dataInicial, new Date()));
		}else if(dataInicial == null && dataFinal!=null){
			dataInicial = new Date();
			Calendar c = Calendar.getInstance();
			c.set(1970, 1, 1);
			dataInicial.setTime(c.getTimeInMillis());
			pesq.add(Restrictions.between("dataCadastro", dataInicial, dataFinal));
		}else if(dataInicial==null && dataFinal ==null){
			dataInicial = new Date();
			Calendar c = Calendar.getInstance();
			c.set(1970, 1, 1);
			dataInicial.setTime(c.getTimeInMillis());
			pesq.add(Restrictions.between("dataCadastro", dataInicial, new Date()));
		}else{
			pesq.add(Restrictions.between("dataCadastro", dataInicial, dataFinal));
		}
		
		
		
		return pesq.list();
	}
}
