package sla.estadistica;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sla.servicio.slaServicio;
import sla.entidades.sla;


@Controller
public class slaEstadis {

	@Autowired
	private slaServicio slaService;
	public String verDetallesEmpleado(@PathVariable(value="orderID") String orderID,Model modelo,RedirectAttributes flash)
	{
		
		String measurement2=null;
		if(orderID.equals("Met")) {
			measurement2="Cumplidos";
		}
		if(orderID.equals("Missed")) {
			measurement2="Incumplidos";
		}
		if(orderID.equals("Pending")) {
			measurement2="Pendiente";
		}
		modelo.addAttribute("titulo","ESTADO SLA: "+measurement2);
		modelo.addAttribute("palabaClave",orderID);
	if(orderID.equals("Missed")) {
		List<sla> Listasla= slaService.findAll(orderID,"Missed Goal");
		//List<sla> Listasla2= slaService.findAll2("WO0000000118006","Missed");
		
		
		modelo.addAttribute("sla",Listasla);
		//modelo.addAttribute("sla2",Listasla2);
		modelo.addAttribute("palabraClave",orderID);
			
		return "ver";
		
	}else {
	
		List<sla> Listasla= slaService.findAll(orderID);
		//List<sla> Listasla2= slaService.findAll2("WO0000000118006","Missed");
		//System.out.println(Listasla2.get(0).getTitle());
		modelo.addAttribute("sla",Listasla);
		//modelo.addAttribute("sla2",Listasla2);
		modelo.addAttribute("palabraClave",orderID);
			
		return "ver";
	}
	}
	
	public String verDet(@PathVariable(value="opcion") String opcion,@PathVariable(value="orderID") String orderID,Model modelo,RedirectAttributes flash)
	{
		String orderID2=null;
		modelo.addAttribute("titulo","ESTADO SLA: "+orderID);
		modelo.addAttribute("palabaClave",orderID);
		List<sla> Listasla= new ArrayList<>();
		if(opcion.equals("Total Nivel 1")) {
		 Listasla= slaService.findAll4();
		}
		
		if(opcion.equals("Total Nivel 2")) {
			 Listasla= slaService.findAll5();
			}
		if(orderID.equals("Missed")) {
			 orderID2="Missed Goal";
		}
		 
		List<sla> Listasla2= new ArrayList<>();
			for (int i=0;i<Listasla.size();i++) {
			
			      if(Listasla.get(i).getMeasurement().equals(orderID)||Listasla.get(i).getMeasurement().equals(orderID2)) {
			    	  sla account = new sla();
					account.setOrderID(Listasla.get(i).getOrderID());
						account.setTitle(Listasla.get(i).getTitle());
						account.setMeasurement(Listasla.get(i).getMeasurement());
						account.setAsgrp(Listasla.get(i).getAsgrp());
						account.setCategoria1(Listasla.get(i).getCategoria1());
						account.setCategoria2(Listasla.get(i).getCategoria2());
						account.setCategoria3(Listasla.get(i).getCategoria3());			
						Listasla2.add(account);   	 
			    	  	 
			      }
			    }
			modelo.addAttribute("sla",Listasla2);
	
		modelo.addAttribute("palabraClave",orderID);
			
		return "ver";
	}


	

@GetMapping({"/dashs/"})
public String dash(@Param("e")String e,@Param("w")String w,@Param("m")String m,@Param("at")String at,@Param("c")String c,@Param("a")String a,@Param("p")String p,Model modelo,RedirectAttributes flash) {
if(p!=null && !p.isEmpty()) {
	if(p.equals("ESTADISTICA DE TICKETS TOTAL")){
		return verDetallesEmpleado("Missed", modelo, flash);
		}
	if(p.equals("Total Nivel I")||p.equals("Total Nivel II")){
		 System.out.println("Ingeso");
		return verDet(p,"Missed", modelo, flash);
		}
		return ver(p.trim(),"Missed", modelo, null);
		
	}else if(a!=null && !a.isEmpty()){
		if(a.equals("ESTADISTICA DE TICKETS TOTAL")){
			return verDetallesEmpleado("Detached", modelo, flash);
			}
		if(a.equals("Total Nivel I")||a.equals("Total Nivel II")){
			return verDet(a,"Detached", modelo, flash);
			}
		return ver(a.trim(),"Detached", modelo, null);
	}else if(c!=null && !c.isEmpty() ){
		if(c.equals("ESTADISTICA DE TICKETS TOTAL")){
			return verDetallesEmpleado("Met", modelo, flash);
			}
		if(c.equals("Total Nivel I")||c.equals("Total Nivel II")){
			return verDet(c,"Met", modelo, flash);
			}
		return ver(c.trim(),"Met", modelo, null);
	}else if(at!=null && !at.isEmpty()){
		if(at.equals("ESTADISTICA DE TICKETS TOTAL")){
			return verDetallesEmpleado("Attached", modelo, flash);
			}
		if(at.equals("Total Nivel I")||at.equals("Total Nivel II")){
			return verDet(at,"Attached", modelo, flash);
			}
		return ver(at.trim(),"Attached", modelo, null);
	}else if(m!=null && !m.isEmpty()){
		if(m.equals("ESTADISTICA DE TICKETS TOTAL")){
			return verDetallesEmpleado("Missed Goal", modelo, flash);
			}
		if(m.equals("Total Nivel I")||m.equals("Total Nivel II")){
			return verDet(m,"Missed Goal", modelo, flash);
			}
		return ver(m.trim(),"Missed Goal", modelo, null);
	}else if(w!=null && !w.isEmpty()){
		if(w.equals("ESTADISTICA DE TICKETS TOTAL")){
			return verDetallesEmpleado("Warning", modelo, flash);
			}
		if(w.equals("Total Nivel I")||w.equals("Total Nivel II")){
			return verDet(w,"Warning", modelo, flash);
			}
		return ver(w.trim(),"Warning", modelo, null);
	}else if(e!=null && !e.isEmpty()){
		if(e.equals("ESTADISTICA DE TICKETS TOTAL")){
			return verDetallesEmpleado("Pending", modelo, flash);
			}
		if(e.equals("Total Nivel I")||e.equals("Total Nivel II")){
			return verDet(e,"Pending", modelo, flash);
			}
		return ver(e.trim(),"Pending", modelo, null);
	}else {
	
	List<sla> menu= slaService.findAll7();
	List<sla> menu2= slaService.findAll8();
	List<sla> sla= slaService.findAll("Gestion Reclamos Informe Primera Visita");
	List<sla> sla2= slaService.findAll("Gestion Reclamos Coordina Visita");
	List<sla> sla3= slaService.findAll("Gestion Reclamos GCC_PYME");
	List<sla> sla4= slaService.findAll("Gestion Reclamos Coordina Cierre");
	List<sla> sla5= slaService.findAll("Gestion Reclamos Revision Informe");
	List<sla> sla6= slaService.findAll("Gestion Reclamos ROAMING");
	List<sla> sla7= slaService.findAll("Gestion Reclamos INDIVIDUAL");
	List<sla> sla8= slaService.findAll("Gestion Reclamos N1");
	List<sla> sla9= slaService.findAll("Gestion Reclamos Revision Nuevo Ambiente");
	//List<sla> sla10= slaService.findAll("Gestion Reclamos Revision Informe");
	List<sla> sla11= slaService.findAll("Gestion Reclamos Revision Nuevo");
	List<sla> sla12= slaService.findAll("Gestion Reclamos Solucion Tipo I");
	List<sla> sla13= slaService.findAll("Gestion Reclamos Solucion Tipo II");
	List<sla> sla14= slaService.findAll("Gestion Reclamos Solucion Tipo III");
	List<sla> sla15= slaService.findAll("Gestion Reclamos Solucion Tipo VI");
	List<sla> sla16= slaService.findAll("Gestion Reclamos VIP");
	List<sla> sla17= slaService.findAll("Gestion Reclamos Solucion Tipo V");
	List<sla> Listasla= slaService.findAll3();
	List<sla> Listasla2= slaService.findAll();
	List<sla> Listasla3= slaService.findAll4();

	List<sla> Listasla5= slaService.findAll5();
	int totalT=Listasla.size();
	int t=Listasla2.size();
	modelo.addAttribute("inicio",Listasla2);
	modelo.addAttribute("totalT",totalT);
	modelo.addAttribute("lT",t);
	modelo.addAttribute("sla",sla);
	modelo.addAttribute("sla2",sla2);
	modelo.addAttribute("sla3",sla3);
	modelo.addAttribute("sla4",sla4);
	modelo.addAttribute("sla5",sla5);
	modelo.addAttribute("sla6",sla6);
	modelo.addAttribute("sla7",sla7);
	modelo.addAttribute("sla8",sla8);
	modelo.addAttribute("sla9",sla9);

	modelo.addAttribute("sla11",sla11);
	modelo.addAttribute("sla12",sla12);
	modelo.addAttribute("sla13",sla13);
	modelo.addAttribute("sla14",sla14);
	modelo.addAttribute("sla15",sla15);
	modelo.addAttribute("sla16",sla16);
	modelo.addAttribute("sla17",sla17);
	modelo.addAttribute("sla18",Listasla3);
	modelo.addAttribute("sla19",Listasla5);
	modelo.addAttribute("sla20",Listasla);
	modelo.addAttribute("sla21",Listasla2);
	modelo.addAttribute("menu",menu);
	modelo.addAttribute("menu2",menu2);
return "dash";}
}
@GetMapping("/ver/orderID/{orderID}/measurement/{measurement}")
public String ver(@PathVariable(value="orderID") String orderID,@PathVariable(value="measurement") String measurement,Model modelo,RedirectAttributes flash)
{
	
String measurement2=null;
if(measurement.equals("Met")) {
	measurement2="Cumplidos";
}
if(measurement.equals("Missed")) {
	measurement2="Incumplidos";
}
if(measurement.equals("Pending")) {
	measurement2="Pendiente";
}
modelo.addAttribute("titulo","ESTADO SLA: "+measurement2);
modelo.addAttribute("palabaClave",orderID);
System.out.println(orderID);
if(orderID.contains("W")) {
	System.out.println("true");
	List<sla> Listasla= slaService.findAll6(orderID, measurement);
	modelo.addAttribute("sla",Listasla);
	//modelo.addAttribute("sla2",Listasla2);
	modelo.addAttribute("palabraClave",orderID);
	return "/ver";
}
if(measurement.equals("Missed")) {
	List<sla> Listasla= slaService.findAll2(orderID,measurement, "Missed Goal");
	modelo.addAttribute("sla",Listasla);
	//modelo.addAttribute("sla2",Listasla2);
	modelo.addAttribute("palabraClave",orderID);
	return "ver";
}
else {
	List<sla> Listasla= slaService.findAll2(orderID, measurement);
	//List<sla> Listasla2= slaService.findAll2("WO0000000118006","Missed");
	//System.out.println(Listasla2.get(0).getTitle());
	modelo.addAttribute("sla",Listasla);
	//modelo.addAttribute("sla2",Listasla2);
	modelo.addAttribute("palabraClave",orderID);
	return "ver";
}
	
}
@GetMapping({"/graficos/{palabraClave}"})

public String grafico(@Param("e")String e,@Param("w")String w,@Param("m")String m,@Param("at")String at,@Param("c")String c,@Param("a")String a,@Param("p")String p,@PathVariable(value="palabraClave") String orderID,Model modelo,RedirectAttributes flash) {
	
	
	if(p!=null && !p.isEmpty()) {
		
			return ver(p.trim(),"Missed", modelo, null);
			
		}else if(a!=null && !a.isEmpty()){
			return ver(a.trim(),"Detached", modelo, null);
		}else if(c!=null && !c.isEmpty() ){
			
			return ver(c.trim(),"Met", modelo, null);
		}else if(at!=null && !at.isEmpty()){
			
			return ver(at.trim(),"Attached", modelo, null);
		}else if(m!=null && !m.isEmpty()){
			
			return ver(m.trim(),"Missed Goal", modelo, null);
		}else if(w!=null && !w.isEmpty()){
			
			return ver(w.trim(),"Warning", modelo, null);
		}else if(e!=null && !e.isEmpty()){
			
			return ver(e.trim(),"Pending", modelo, null);
		}else {
		System.out.println(p);
		List<sla> sla= slaService.findAll(orderID);
		List<sla> Listasla= slaService.findAll3();
		List<sla> Listasla2= slaService.findAll("");
		int totalT=Listasla.size();
		int t=Listasla2.size();
		modelo.addAttribute("lT",t);
		modelo.addAttribute("inicio",Listasla2);
		modelo.addAttribute("totalT",totalT);
		modelo.addAttribute("sla",sla);
		modelo.addAttribute("palabra",orderID);
		return "grafico";
	}
	
		
}






}
