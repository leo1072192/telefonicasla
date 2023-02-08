package sla.entidades;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
@Table(name="mi_db")
public class sla {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=true,length=60)
private String orderID;	
	@Column(nullable=true,length=60)
private String title;
	@Column(nullable=true,length=60)
private String measurement;
	@Column(nullable=true,length=60)
private String asgrp;
	@Column(nullable=true,length=60)
private String Categoria1;
	@Column(nullable=true,length=60)
private String Categoria2;
	@Column(nullable=true,length=60)
private String Categoria3;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	public String getAsgrp() {
		return asgrp;
	}

	public void setAsgrp(String asgrp) {
		this.asgrp = asgrp;
	}

	public String getCategoria1() {
		return Categoria1;
	}

	public void setCategoria1(String categoria1) {
		Categoria1 = categoria1;
	}

	public String getCategoria2() {
		return Categoria2;
	}

	public void setCategoria2(String categoria2) {
		Categoria2 = categoria2;
	}

	public String getCategoria3() {
		return Categoria3;
	}

	public void setCategoria3(String categoria3) {
		Categoria3 = categoria3;
	}
public sla() {
	super();
}
	

}

