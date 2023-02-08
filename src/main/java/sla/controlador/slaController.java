package sla.controlador;

import java.util.Map;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.univocity.parsers.common.record.Record;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import sla.servicio.slaServicio;
import sla.entidades.sla;

import sla.util.paginacion.PageRender;

@Controller
public class slaController {
	
	
	@Autowired
	private slaServicio slaService;
	

	@GetMapping("/ver/{orderID}")
	//@GetMapping("/ver/orderID/{orderID}/measurement/{measurement}")
	public String verDetallesEmpleado(@PathVariable(value="orderID") String orderID,Model modelo,RedirectAttributes flash)
	{
		
		modelo.addAttribute("titulo","ESTADO SLA: "+orderID);
		modelo.addAttribute("palabaClave",orderID);
		
	if(orderID.equals("cumplidos")) {
		orderID="Met";
	}
	if(orderID.equals("incumplidos")) {
		orderID="Missed";
	}
	if(orderID.equals("pendientes")) {
		orderID="Pending";
	}
	
		List<sla> Listasla= slaService.findAll(orderID);
		//List<sla> Listasla2= slaService.findAll2("WO0000000118006","Missed");
		//System.out.println(Listasla2.get(0).getTitle());
		modelo.addAttribute("sla",Listasla);
		//modelo.addAttribute("sla2",Listasla2);
		modelo.addAttribute("palabraClave",orderID);
		int valida=0;
		for (int i=0;i<Listasla.size();i++) {
		      
		     
		      if(Listasla.get(i).getMeasurement().equals("Detached")) {
		    	 
		    	  	 valida=1;
		      }
		    }
		if(valida>=1) {
			modelo.addAttribute("ti","ESTADO DE TICKET COMPLETO");
		}else {
			modelo.addAttribute("ti","ESTADO DE TICKET INCOMPLETO");
			
			
		}
		return "ver";
	}
	
	public int complete(String sla) {
		int count=0;
		if(sla.equalsIgnoreCase("Met")) {
			count++;
		}
		return count;
	}

	
	@GetMapping({"/","listar",""})
	
	public String listarsla(@RequestParam(name="page",defaultValue="0")int page,Model modelo,@Param("palabraClave")String palabraClave) {
		Long a=0L;
	List<sla> Listasla= slaService.findAll();
	
	
		Pageable pageRequest = PageRequest.of(page,10);
	Page<sla> sla = slaService.findAll(pageRequest);
	Long total=sla.getTotalElements();
	int totalT=Listasla.size();
	System.out.println(sla.getTotalElements());
	
	PageRender<sla> pageRender = new PageRender<>("listar",sla);
	//
modelo.addAttribute("titulo","ESTADO SLA");
	
	modelo.addAttribute("palabraClave",palabraClave);
	modelo.addAttribute("sla",sla);
	modelo.addAttribute("page", pageRender);
	modelo.addAttribute("total", total);
	modelo.addAttribute("totalT", totalT);
	
	if(palabraClave !=null && a ==0L) {
		
		return verDetallesEmpleado(palabraClave.trim(), modelo, null);
		//return "/ver";
	}
	
	
	
	return "listar";
	}

	
	
	
	@GetMapping("/form")

	public String mostrarFormularioDeRegistrarSla(Map<String,Object>modelo) {
		sla Sla= new sla();
		modelo.put("sla", Sla);
		modelo.put("titulo","Registro de sla");
		return "form";
		
	}

	/*@PostMapping("/form")
	
	public String guardarSla(@Validated sla Sla,BindingResult result,Model modelo,RedirectAttributes flash, SessionStatus status) {
		if(result.hasErrors()) {
			modelo.addAttribute("Titulo","Registro Sla");
		return "form";
		
		}
		String mensaje =(Sla.getId()!=null)? "Ticket creado correctamente":"Registrado con exito";
		slaService.save(Sla);
		String orderID=Sla.getOrderID();
	
		status.setComplete();
		flash.addFlashAttribute("sucess",mensaje);
		return verDetallesEmpleado(orderID, modelo, flash);
		
		}
	*/
	@GetMapping("/form/{id}")
	public String editarSla(@PathVariable(value="id") Long id,Map<String, Object>modelo, RedirectAttributes flash){
		sla Sla=null;
		if(id>0) {
			
			Sla=slaService.findOne(id);
			if(Sla==null) {
				flash.addFlashAttribute("error","id del ticket no se encuentra");
				return "redirect:listar";
			}		
			}
		else {
			flash.addFlashAttribute("error","id del ticket nopuede ser cero");
			return "redirect:listar";
		}
		modelo.put("sla", Sla);
		modelo.put("titulo","Edicion de tickets");
		return "form";
	}
	
	@GetMapping("/eliminar/id/{id}/orderID/{orderID}")
	public String eliminarSla(@PathVariable(value = "id") Long id,@PathVariable(value = "orderID") String orderID,RedirectAttributes flash){
		
		if(id > 0) {
			
			slaService.delete(id);
			flash.addFlashAttribute("success","Ticket eliminado con exuito ");
		} 
		 
		return "redirect:/ver/{orderID}";
			
	}
	@PostMapping("/upload")
	public <CSVParser> String uploadData(@RequestParam("file") MultipartFile file,Model modelo) throws Exception {
		
		slaService.deleteAll();
		
		List<sla> Listasla= new ArrayList<>();
		
		InputStream inputStream=file.getInputStream();
		 
		
		CsvParser parser=new CsvParser(parseExcel());
		parser.getDetectedFormat();
		
		List<Record>parseAllRecords = parser.parseAllRecords(inputStream);
		
		for (Record record : parseAllRecords) {
		
			sla account = new sla();
			
			
			account.setOrderID(record.getString("Work Order ID"));
			account.setTitle(record.getString("SVTTitle"));
			account.setMeasurement(record.getString("MeasurementStatus"));
			account.setAsgrp(record.getString("ASGRP"));
			account.setCategoria1(record.getString("Categor�a 1__c"));
			account.setCategoria2(record.getString("Categor�a 2__c"));
			account.setCategoria3(record.getString("Categor�a 3__c"));
			
			
			slaService.save(account);
			Listasla.add(account);
		}
		
		modelo.addAttribute("msj","ESTADO DE TICKET COMPLETO");
		return "redirect:listar";
	}
	 
	 public static CsvParserSettings parseExcel() {
	   CsvParserSettings settings = new CsvParserSettings();
	   
	   settings.getFormat().setLineSeparator("\r\n");
	   settings.getFormat().setDelimiter(';');
	   settings.getFormat().setComment('\0');
	   settings.getDelimitersForDetection();
	   settings.setSkipEmptyLines(false);
	   settings.trimValues(false);
	   settings.setHeaderExtractionEnabled(true);
	   return settings;
	 }
	
}
