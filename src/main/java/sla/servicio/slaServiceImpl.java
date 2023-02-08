package sla.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sla.entidades.sla;
import sla.entidades.grafico;
import sla.repositorio.slaRepositorio;

@Service
public class slaServiceImpl implements slaServicio {
	@Autowired
	private slaRepositorio empleadoRepositorio;
	@Override
	@Transactional(readOnly=true)
	
	public List<sla> findAll(String palabraClave) {
		if(palabraClave !=null) {
			return empleadoRepositorio.findAll(palabraClave);
		
		}else {
		return (List<sla>) empleadoRepositorio.findAll();
		}
	}
	
	
	@Override
	@Transactional(readOnly=true)
	
	public Page<sla> findAll(Pageable pageable) {
		return empleadoRepositorio.findAll(pageable);
		
	}

	@Override
	@Transactional
	public void save(sla sla) {
		empleadoRepositorio.save(sla);
		
	}

	@Override
	public sla findOne(Long id) {
		return empleadoRepositorio.findById(id).orElse(null);
	}
	

	@Override
	@Transactional
	public void delete(Long id) {
		empleadoRepositorio.deleteById(id);
		
	}


	@Override
	public List<sla> findAll2(String palabraClave, String name) {
		return (List<sla>) empleadoRepositorio.findAll2(palabraClave, name);
	}


	@Override
	public List<sla> findAll3() {
		return (List<sla>) empleadoRepositorio.findAll3();
		
	}
	@Override
	public List<sla> findAll4() {
		return (List<sla>) empleadoRepositorio.findAll4();
		
	}
	@Override
	public List<sla> findAll6(String name1,String name2) {
		return (List<sla>) empleadoRepositorio.findAll6(name1,name2);
		
	}
	@Override
	public List<sla> findAll5() {
		return (List<sla>) empleadoRepositorio.findAll5();
		
	}


	@Override
	public List<sla> findAll() {
		return (List<sla>) empleadoRepositorio.findAll();
	}


	@Override
	public List<sla> findAll2(String palabraClave, String name, String name2) {
		return (List<sla>) empleadoRepositorio.findAll2(palabraClave, name, name2);
	}


	@Override
	public List<sla> findAll(String name, String name2) {
		return (List<sla>) empleadoRepositorio.findAll(name, name2);
	}


	@Override
	public List<sla> findAll7() {
		return (List<sla>) empleadoRepositorio.findAll7();
	}
	@Override
	public List<sla> findAll8() {
		return (List<sla>) empleadoRepositorio.findAll8();
	}


	@Override
	public void deleteAll() {
		empleadoRepositorio.deleteAll();
	}


	

}
