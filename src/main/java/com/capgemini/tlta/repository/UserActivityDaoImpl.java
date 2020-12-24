package com.capgemini.tlta.repository;

public class UserActivityDaoImpl{

//	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("technology-learning-and-tracking-app");
//	private RegisterUserDao loginDao = new RegisterUserDaoImpl();
//	private LearningActivityDao learningActivityDao = new LearningActivityDaoImpl();
//	//TODO this methode is not working
//	
//	public Integer uploadCerificate(String filePath,Integer userId,Integer activityId) throws PersistenceException {
//		EntityManager em = emf.createEntityManager();
//		try {
//			em.getTransaction().begin();
////			userActivity.setStatus("complete");
////			userActivity.setCertificate(filePath);
////			UserActivity updatedUserActivity = em.merge(userActivity);
////			em.flush();
////			
//			
//			Query query = em.createQuery("UPDATE UserActivity SET certificate = :filePath, status = :complete"
//		              + " WHERE e.userId = :uid AND e.activityId = :aid");
//		      query.setParameter("filePath", filePath);
//		      query.setParameter("complete", "complete");
//		      query.setParameter("uid", userId);
//		      query.setParameter("aid", activityId);
//		      int rowsUpdated = query.executeUpdate();
//		      System.out.println("user_activity entitiy Updated: " + rowsUpdated);
//		      em.getTransaction().commit();
//			return rowsUpdated;
//		} catch (PersistenceException e) {
//			em.getTransaction().rollback();
//			throw e;
//		} catch (Exception e) {
//			throw e;
//		} finally {
//			em.close();
//			
//		}
//
//	}
//
//	
//	public UserActivity updateStatus(UserActivity userActivity, String status) throws PersistenceException {
//		EntityManager em = emf.createEntityManager();
//		try {
//			em.getTransaction().begin();
//			userActivity.setStatus(status);		
//			UserActivity updatedUserActivity = em.merge(userActivity);
//			em.flush();
//			em.getTransaction().commit();
//			return updatedUserActivity;
//		} catch (PersistenceException e) {
//			em.getTransaction().rollback();
//			throw e;
//		} catch (Exception e) {
//			throw e;
//		} finally {
//			em.close();
//			
//		}
//	}
//	public Integer userRegisterToLearningActivity(UserActivity userActivity, Integer userId, Integer activityId) throws PersistenceException {
//		EntityManager em = emf.createEntityManager();
//		
//		try {
//			em.getTransaction().begin();
//			RegisterUser newUser = loginDao.getUserById(userId);
//			LearningActivity newActivity = learningActivityDao.searchLearningActivityById(activityId);
//			userActivity.setLearningActivity(newActivity);
//			userActivity.setLogin(newUser);
//			em.persist(userActivity);
//			em.getTransaction().commit();
//			return userActivity.getUserActivityId();
//		}catch(PersistenceException e) {
//			em.getTransaction().rollback();
//			throw e;
//		}catch(Exception e) {
//			throw e;
//		}finally {
//			em.close();
//		}
//		
//		
//	}

}
