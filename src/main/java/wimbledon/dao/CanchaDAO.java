package wimbledon.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import wimbledon.modelo.Cancha;
import wimbledon.vista.ListadoPartidos;

@Repository
@Scope("singleton")
public class CanchaDAO implements ICanchaDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private static final Logger log = LoggerFactory.getLogger(CanchaDAO.class);

	@Override
	public void crear(Cancha entity) {
		entityManager.persist(entity);
	}

	@Override
	public void actualizar(Cancha entity) {
		entityManager.merge(entity);
	}

	@Override
	public void eliminar(Cancha entity) {
		entityManager.remove(entity);
	}

	@Override
	public Cancha consultarPorId(long idEntity) {
		return entityManager.find(Cancha.class, idEntity);
	}

	@Override
	public List<Cancha> consultarTodos() {
		String jpql = "SELECT cancha FROM Cancha cancha";
		return entityManager.createQuery(jpql).getResultList();
	}
	
	@Override
	public List<Cancha> consultarCanchasDisponibles(Date fecha) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha); // Configuramos la fecha que se recibe
		calendar.add(Calendar.HOUR, -3);  // numero de días a añadir, o restar en caso de días<0
		
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(fecha); // Configuramos la fecha que se recibe
		calendar1.add(Calendar.HOUR, 4);  // numero de días a añadir, o restar en caso de días<01
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
		log.info("-------------------------------"+sdf.format(calendar.getTime()));
		
		String fi = sdf.format(calendar.getTime());//calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DAY_OF_MONTH)+" "+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND);
		String ff = sdf.format(calendar1.getTime());//calendar1.get(calendar1.YEAR)+"-"+calendar1.get(calendar1.MONTH)+"-"+calendar1.get(calendar1.DAY_OF_MONTH)+" "+calendar1.get(calendar1.HOUR_OF_DAY)+":"+calendar1.get(calendar1.MINUTE)+":"+calendar1.get(calendar1.SECOND);
		
		String jpql = "SELECT cancha FROM Cancha cancha WHERE cancha.idcancha NOT IN (SELECT reserva.cancha FROM Reservacancha reserva WHERE ('"+fi+"' BETWEEN reserva.fechainicio AND reserva.fechafin OR '"+ff+"' BETWEEN reserva.fechainicio AND reserva.fechafin))";
		log.info("<<<<<<<<<<<<<<<<<<< "+jpql);
		return entityManager.createQuery(jpql).getResultList();
	}

}
