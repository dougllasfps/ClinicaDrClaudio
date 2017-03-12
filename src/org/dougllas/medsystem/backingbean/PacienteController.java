package org.dougllas.medsystem.backingbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.dougllas.medsystem.dao.PacienteDao;
import org.dougllas.medsystem.generic.BackingBeanGenerico;
import org.dougllas.medsystem.model.Paciente;
import org.dougllas.medsystem.model.Sexo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("pacienteController")
@Scope("view")
public class PacienteController extends BackingBeanGenerico implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private Paciente paciente;

	private Paciente pacienteFiltro;

	@Autowired
	private PacienteDao pacienteDao;

	private List<Paciente> pacientes;

	private Date dataInicial = new Date(), dataFinal  = new Date();

	private Integer select;
	
	private String order;

	@PostConstruct
	public void init() {
		paciente = new Paciente();
		this.pacientes = pacienteDao.todos();
		pacienteFiltro = new Paciente();
		setState(SEARCH_STATE);
		Calendar c = Calendar.getInstance();
		c.set(1970, 1, 1);
		dataInicial.setTime(c.getTimeInMillis());
	}

	public void novo() {
		paciente = new Paciente();
		paciente.setDataCadastro(new Date());
		paciente.setNumPaciente(geraProximoNumero());
		setState(ADD_STATE);
	}

	public void salvar() {
		pacienteDao.insere(paciente);
		adicionaMensagemDeInformacao("Paciente salvo com sucesso!");
		setState(SEARCH_STATE);
		lista();
	}

	public void pesquisa() {
		
		if(getOrder().trim().equals("")){
			setOrder("nome");
		}
			
		List<Paciente> pesqList = pacienteDao.listByExample(pacienteFiltro, select, dataInicial, dataFinal, order);

		if (pesqList != null && pesqList.size() != 0) {
			this.pacientes = pesqList;
		} else {
			adicionaMensagemDeErro("Paciente(s) n�o encontrado(s) tente outros parametros!");
		}
	}

	public void listar() {
		lista();
		setState(SEARCH_STATE);
	}

	public void voltar() {
		setState(SEARCH_STATE);
	}

	public void prepareEditar(Paciente paciente) {
		this.paciente = paciente;
		setState(EDIT_STATE);
	}

	public void editar() {
		pacienteDao.atualiza(paciente);
		adicionaMensagemDeInformacao("Paciente atualizado com sucesso!");
		setState(SEARCH_STATE);
		lista();
	}

	public void lista() {
		this.pacientes = pacienteDao.todos();
	}

	public List<String> getConvenios() {
		String sql = "SELECT DISTINCT p.convenio FROM Paciente p";
		List<Paciente> lista = pacienteDao.listPesqParam(sql,
				new HashMap<String, Object>());
		List<String> listaConvenios = new ArrayList<String>();
		for (Paciente p : lista)
			listaConvenios.add(p.getConvenio());
		return listaConvenios;
	}

	public Integer geraProximoNumero() {
		String sql = "SELECT p FROM Paciente p";
		List<Paciente> lista = pacienteDao.listPesqParam(sql,
				new HashMap<String, Object>());
		Integer proximo = 0;
		for (Paciente p : lista) {
			if (p.getNumPaciente() > proximo) {
				proximo = p.getNumPaciente();
			}
		}
		proximo = proximo + 1;
		return proximo;
	}

	public List<String> getSexos() {
		List<String> sexos = new ArrayList<String>();
		sexos.add(Sexo.F.toString());
		sexos.add(Sexo.M.toString());
		sexos.add(Sexo.ND.toString());
		return sexos;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public PacienteDao getPacienteDao() {
		return pacienteDao;
	}

	public void setPacienteDao(PacienteDao pacienteDao) {
		this.pacienteDao = pacienteDao;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public Paciente getPacienteFiltro() {
		return pacienteFiltro;
	}

	public void setPacienteFiltro(Paciente pacienteFiltro) {
		this.pacienteFiltro = pacienteFiltro;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Integer getSelect() {
		return select;
	}

	public void setSelect(Integer select) {
		this.select = select;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}
