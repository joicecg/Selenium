package WebDriver;

public class Resolucao {
	private String plano;
	private String idEq;
	private String eq;
	private String res;
	private String user;
	private String cor;
	
	public Resolucao(String plano,  String eq, String idEq, String res,String cor, String user) {
		this.plano = plano;
		this.idEq = idEq;
		this.eq = eq;
		this.res = res;
		this.user = user;
		this.cor = cor;
	}

	public String getPlano() {
		return plano;
	}

	public void setPlano(String plano) {
		this.plano = plano;
	}

	public String getIdEq() {
		return idEq;
	}

	public void setIdEq(String idEq) {
		this.idEq = idEq;
	}

	public String getEq() {
		return eq;
	}

	public void setEq(String eq) {
		this.eq = eq;
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}
	
	public String toString(){
		String cor;
		if(getCor().equals("1")){
			 cor = "Sim";
		} else cor = "Não";
		
		return "Plano de aula: " + getPlano() 
		+ "\nUsuário: " + getUser()
		+ "\nID da equação:" +getIdEq() 
		+ "\nEquação: " + getEq()
		+ "\nResposta do aluno: " + getRes()
		+ "\nEstá correto? " + cor;
		
	}

	
	

}
