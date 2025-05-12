package Clases;

import java.time.LocalDate;

public class Pedido {
	private int idpedido;
	private Cliente idcliente;
	private double precioTotal;
	private String direccionEnvio;
	private LocalDate fecha;
	public Pedido(int idpedido, Cliente idcliente, double precioTotal, String direccionEnvio, LocalDate fecha) {
		super();
		this.idpedido = idpedido;
		this.idcliente = idcliente;
		this.precioTotal = precioTotal;
		this.direccionEnvio = direccionEnvio;
		this.fecha = fecha;
	}
	public Pedido() {
		super();
	}
	public int getIdpedido() {
		return idpedido;
	}
	public void setIdpedido(int idpedido) {
		this.idpedido = idpedido;
	}
	public Cliente getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(Cliente idcliente) {
		this.idcliente = idcliente;
	}
	public double getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	public String getDireccionEnvio() {
		return direccionEnvio;
	}
	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	@Override
	public String toString() {
		return "Pedido [idpedido=" + idpedido + ", precioTotal=" + precioTotal + ", direccionEnvio=" + direccionEnvio
				+ ", fecha=" + fecha + "]";
	}
}
 