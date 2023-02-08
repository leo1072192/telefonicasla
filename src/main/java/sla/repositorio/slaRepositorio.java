package sla.repositorio;
import sla.entidades.sla;
//import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
//import sla.entidades.sla;
public interface slaRepositorio extends PagingAndSortingRepository <sla,Long> {
@Query("SELECT p from sla p  Where p.measurement LIKE %?1 AND p.measurement <> 'Attached'"+ " OR p.orderID LIKE %?1 AND p.measurement <> 'Attached' "+ " OR p.title LIKE %?1 AND p.measurement <> 'Attached'")
public List<sla> findAll(String palabraClave);
@Query("SELECT p from sla p Where p.measurement LIKE %?1" +" OR p.measurement LIKE %?2" )
public List<sla> findAll(String name,String name2);
@Query("SELECT p from sla p Where p.title LIKE %?1" +" AND p.measurement LIKE %?2" )
public List<sla> findAll2(String palabraClave,String name);

@Query("SELECT p from sla p Where p.title LIKE %?1 AND p.measurement LIKE %?2"+" OR p.title LIKE %?1 AND p.measurement LIKE %?3" )
public List<sla> findAll2(String palabraClave,String name,String name2);

@Query("SELECT p FROM sla p ")
public List<sla> findAll();

@Query("SELECT p FROM sla p WHERE p.measurement <> 'Attached'  GROUP BY p.orderID")
public Page<sla> findAll(Pageable pageable);
@Query("SELECT p FROM sla p GROUP BY p.orderID")
public List<sla> findAll3();
@Query("SELECT p from sla p Where p.title LIKE 'Gestion Reclamos INDIVIDUAL'"+ "OR p.title LIKE 'Gestion Reclamos N1'"+ " OR p.title LIKE 'Gestion Reclamos GCC_PYME'" + "OR p.title LIKE 'Gestion Reclamos ROAMING'"+ "OR p.title LIKE 'Gestion Reclamos VIP'")
public List<sla> findAll4();
@Query("SELECT p from sla p Where p.title LIKE 'Gestion Reclamos Informe Primera Visita'"+ "OR p.title LIKE 'Gestion Reclamos Coordina Visita'"+ "OR p.title LIKE 'Gestion Reclamos Coordina Cierre'"+ "OR p.title LIKE 'Gestion Reclamos Revision Informe'"
		+ "OR p.title LIKE 'Gestion Reclamos Revision Nuevo'"+ "OR p.title LIKE 'Gestion Reclamos Solucion Tipo I'"+ "OR p.title LIKE 'Gestion Reclamos Solucion Tipo II'"+ "OR p.title LIKE 'Gestion Reclamos Solucion Tipo III'"+ "OR p.title LIKE 'Gestion Reclamos Solucion Tipo V'"
		+ "OR p.title LIKE 'Gestion Reclamos Solucion Tipo VI'")
public List<sla> findAll5();
@Query("SELECT p from sla p Where p.orderID LIKE %?1 AND p.measurement  LIKE %?2")
public List<sla> findAll6(String name1,String name2);

@Query("SELECT p from sla p WHERE p.title LIKE 'Gestion Reclamos INDIVIDUAL' AND p.measurement <> 'Attached' AND p.measurement <> 'Detached'"+ "OR p.title LIKE 'Gestion Reclamos N1' AND p.measurement <> 'Attached' AND p.measurement <> 'Detached'"+ " OR p.title LIKE 'Gestion Reclamos GCC_PYME' AND p.measurement <> 'Attached' AND p.measurement <> 'Detached'" + "OR p.title LIKE 'Gestion Reclamos ROAMING' AND p.measurement <> 'Attached' AND p.measurement <> 'Detached'"+ "OR p.title LIKE 'Gestion Reclamos VIP' AND p.measurement <> 'Attached' AND p.measurement <> 'Detached'  GROUP BY p.title")
public List<sla> findAll7();
@Query("SELECT p from sla p Where p.title LIKE 'Gestion Reclamos Informe Primera Visita'"+ "OR p.title LIKE 'Gestion Reclamos Coordina Visita'"+ "OR p.title LIKE 'Gestion Reclamos Coordina Cierre'"+ "OR p.title LIKE 'Gestion Reclamos Revision Informe'"
		+ "OR p.title LIKE 'Gestion Reclamos Revision Nuevo'"+ "OR p.title LIKE 'Gestion Reclamos Solucion Tipo I'"+ "OR p.title LIKE 'Gestion Reclamos Solucion Tipo II'"+ "OR p.title LIKE 'Gestion Reclamos Solucion Tipo III'"+ "OR p.title LIKE 'Gestion Reclamos Solucion Tipo V' "
		+ "OR p.title LIKE 'Gestion Reclamos Solucion Tipo VI' GROUP BY p.title")
public List<sla> findAll8();
@Query("DELETE from sla ")
public List<sla> findAlldelete();
}


