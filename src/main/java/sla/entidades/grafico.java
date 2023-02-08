package sla.entidades;

public class grafico {
	private String Name;
	private int Completo;
	private int Pendiente;
	private int Cancelado;
	private int total;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getCompleto() {
		return Completo;
	}
	public void setCompleto(int completo) {
		Completo = completo;
	}
	public int getPendiente() {
		return Pendiente;
	}
	public void setPendiente(int pendiente) {
		Pendiente = pendiente;
	}
	public int getCancelado() {
		return Cancelado;
	}
	public void setCancelado(int cancelado) {
		Cancelado = cancelado;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public grafico() {
		super();
		// TODO Auto-generated constructor stub
	}
}
