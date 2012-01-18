package demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DBManager {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public void init() {
		emf = Persistence.createEntityManagerFactory("./db/mydb.odb");
	    em = emf.createEntityManager();	    
	}
	
	public void close() {
		if(em != null) {
			em.close();
		}
		if(emf != null) {
			emf.close();
		}
	}
	
	public void save(Device device) {
		em.getTransaction().begin();
		em.persist(device);
		em.getTransaction().commit();
	}
	
	public void del(String id) {
		Query q1 = em.createQuery("SELECT d FROM Device d WHERE d.id=?1", Device.class);
		q1.setParameter(1, id);
		Device d = (Device)q1.getSingleResult();
		em.remove(d);
	}
	
	public Device query(String id) {
		Query q1 = em.createQuery("SELECT d FROM Device d WHERE d.id=?1", Device.class);
		q1.setParameter(1, id);
		Device d = (Device)q1.getSingleResult();
		return  d;
	}
	
	public List<Device> query() {
		Query q1 = em.createQuery("SELECT d FROM Device d", Device.class);
		List<Device> list = (List<Device>)q1.getResultList();
		return list;
	}
	
	public Device modify(String id, Device device) {
		Query q1 = em.createQuery("SELECT d FROM Device d WHERE d.id=?1", Device.class);
		q1.setParameter(1, id);
		Device d = (Device)q1.getSingleResult();
		
		em.getTransaction().begin();
		d.setIp(device.getIp());
		d.setPort(device.getPort());
		d.setDesc(device.getDesc());
		em.getTransaction().commit();
		
		return d;
	}
	
	public static void main(String[] args) {
		DBManager manager = new DBManager();
		manager.init();
		Device d = new Device();
		d.setId("id1");
		d.setIp("127.0.0.1");
		d.setPort(4433);
		d.setDesc("test");
		
		Device d2 = new Device();
		d2.setId("id2");
		d2.setIp("127.0.0.2");
		d2.setPort(4433);
		d2.setDesc("test2");
		
		//manager.save(d);
		//manager.save(d2);
		
		Device device = manager.query("id1");
		System.out.println(device);
		
//		List<Device> list = manager.query();
//		for(Device temp : list) {
//			System.out.println(temp);
//		}
		
		
		
		manager.close();
	}

}
