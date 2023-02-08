package sla.servicio;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sla.entidades.sla;
public interface slaServicio {
	
	public List<sla> findAll(String palabraClave);
	public List<sla> findAll();
	public List<sla> findAll(String name,String name2);
	public List<sla> findAll2(String palabraClave,String name);
	public List<sla> findAll2(String palabraClave,String name,String name2);
	public List<sla> findAll3();
	public List<sla> findAll4();
	public List<sla> findAll5();
	public List<sla> findAll6(String name1,String name2);
	public List<sla> findAll7();
	public List<sla> findAll8();
	public void deleteAll();
	public Page<sla> findAll(Pageable pageable);
	
	public void save(sla sla);
	public sla findOne(Long id);
	
	public void delete(Long id);
	
}
