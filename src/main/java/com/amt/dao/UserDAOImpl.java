package com.amt.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amt.app.Constants;
import com.amt.dto.AddCustomerDTO;
import com.amt.dto.AddOrEditDTO;
import com.amt.dto.AddUserDTO;
import com.amt.model.User;
import com.amt.model.UserType;
import com.amt.model.EmployeeRole;
import com.amt.util.SessionFactorySingleton;
import com.amt.util.PasswordUtil;

public class UserDAOImpl implements UserDAO, Constants {
	private Logger objLogger = LoggerFactory.getLogger(UserDAOImpl.class);

	public UserDAOImpl() {
		super();
	}


	//
	// ###
	@Override // 20210820 completed
	public User getByUsername(String sUsername) throws SQLException, HibernateException {
		String sMethod = csCRT + "getByRecordIdentifer(): ";
		objLogger.trace(csCR + sMethod + "Entered: sUsername: [" + sUsername + "]");

		objLogger.debug(sMethod + "getting SessionFactorySingleton.getSessionFactory()");
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		//sHQL = "FROM UserType ut WHERE ut.userType = :userType";
		String sHQL = "FROM User u WHERE u.username = :username";
		objLogger.debug(sMethod + "sHQL: [" + sHQL + "]" + " param: sRecordIdentifier: [" + sUsername + "]");

		try {
			User objUser = (User) session.createQuery(sHQL).setParameter("username", sUsername).getSingleResult();
			objLogger.debug(sMethod + "objUser: [" + objUser.toString() + "]");

			// Hibernate automatically return the UserRole object when getting the User.
			// Do not need to do a separate read.

			tx.commit();
			return objUser;

		} catch (Exception e) {
			objLogger.error(sMethod + "Exception: cause: [" + e.getCause() + "] class name [" + e.getClass().getName()
					+ "] [" + e.toString() + "]");
			objLogger.error(sMethod + "Exception: toString: [" + e.toString() + " message: [" + e.getMessage() + "]");
			return null;
		} finally {
			session.close();
		}
	}

	
	public User addRecord(AddOrEditDTO objAddOrEditDTO) throws SQLException, HibernateException {
		String sMethod = csCRT + "addRecord(): ";
		objLogger.trace(csCR + sMethod + "Entered");

		objLogger.debug(sMethod + "objAddOrEditDTO: [" + objAddOrEditDTO.toString() + "]");

		// by this time the service layer would have validated the parameters
		String sUsername = objAddOrEditDTO.getDataElement(csUserTblUsername);
		String sFirstName = objAddOrEditDTO.getDataElement(csUserTblFirstName);
		String sLastName = objAddOrEditDTO.getDataElement(csUsrTblLastName);
		String sPassword = objAddOrEditDTO.getDataElement(csUserTblPassword);
		String sPasswordSalt = objAddOrEditDTO.getDataElement(csUserTblPasswordSalt);
		String sEmail = objAddOrEditDTO.getDataElement(csUserTblEmail);		
		String sUserType = objAddOrEditDTO.getDataElement(csUserTblUserType);
		String sEmployeeRole = objAddOrEditDTO.getDataElement(csEmployeeRolesTblEmployeeRole);

		User objNewUser = new User(sUsername, sPassword, sPasswordSalt, sFirstName, sLastName, sEmail);
		objLogger.debug(sMethod + "objNewUser: [" + objNewUser.toString() + "]");
		
		// get UserType object
		UserTypeDAOImpl objUserTypeDAOImpl = new UserTypeDAOImpl();
		UserType objUserType = objUserTypeDAOImpl.getByRecordIdentifer(sUserType);
		objNewUser.setUserType(objUserType);
		objLogger.debug(sMethod + "objUserType: [" + objUserType.toString() + "]");
		
		if (objUserType.getUserType().equalsIgnoreCase(csarUserType[enumUserType.EMPLOYEE.pos])) {
			// get EmployeeRole object
			EmployeeRoleDAOImpl objEmployeeRoleDAOImpl = new EmployeeRoleDAOImpl();
			EmployeeRole objEmployeeRole = objEmployeeRoleDAOImpl.getByRecordIdentifer(sEmployeeRole);
			objNewUser.setEmployeeRole(objEmployeeRole);
			objLogger.debug(sMethod + "objEmployeeRole: [" + objEmployeeRole.toString() + "]");				
		} else {
			objNewUser.setEmployeeRole(new EmployeeRole());
		}

		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		try {
			
			objLogger.debug(sMethod + "Adding final objNewUser: [" + objNewUser.toString() + "] to the database.");

			session.persist(objNewUser);
			tx.commit();
			return objNewUser;

		} catch (Exception e) {
			objLogger.error(sMethod + "Exception: cause: [" + e.getCause() + "] class name [" + e.getClass().getName()
					+ "] [" + e.toString() + "]");
			objLogger.error(sMethod + "Exception: toString: [" + e.toString() + " message: [" + e.getMessage() + "]");
			return null;
		} finally {
			session.close();
		}

	}

	
	//
	// ###
	@Override // 20210819 1223 working method added user using Admin drive through service
				// layer
	public User addUser(AddUserDTO objAddUserDTO) throws SQLException, HibernateException {
		String sMethod = csCRT + "addRecord(): ";
		objLogger.trace(csCR + sMethod + "Entered");

		objLogger.debug(sMethod + "objAddUserDTO: [" + objAddUserDTO.toString() + "]");

		// by this time the service layer would have validated the parameters
		String sUsername = objAddUserDTO.getUsername();
		String sFirstName = objAddUserDTO.getFirstName();
		String sLastName = objAddUserDTO.getLastName();
		String sPassword = objAddUserDTO.getPassword();
		String sPasswordSalt = objAddUserDTO.getPasswordSalt();
		String sEmail = objAddUserDTO.getEmail();		
		String sUserType = objAddUserDTO.getUserType();
		String sEmployeeRole = objAddUserDTO.getEmployeeRole();

		User objNewUser = new User(sUsername, sPassword, sPasswordSalt, sFirstName, sLastName, sEmail);
		objLogger.debug(sMethod + "objNewUser: [" + objNewUser.toString() + "]");
		
		// get UserType object
		UserTypeDAOImpl objUserTypeDAOImpl = new UserTypeDAOImpl();
		UserType objUserType = objUserTypeDAOImpl.getByRecordIdentifer(sUserType);
		objNewUser.setUserType(objUserType);
		objLogger.debug(sMethod + "objUserType: [" + objUserType.toString() + "]");
		
		// get EmployeeRole object
		EmployeeRoleDAOImpl objEmployeeRoleDAOImpl = new EmployeeRoleDAOImpl();
		EmployeeRole objEmployeeRole = objEmployeeRoleDAOImpl.getByRecordIdentifer(sEmployeeRole);
		objNewUser.setEmployeeRole(objEmployeeRole);
		objLogger.debug(sMethod + "objEmployeeRole: [" + objEmployeeRole.toString() + "]");				

		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		try {
			
			objLogger.debug(sMethod + "Adding final objNewUser: [" + objNewUser.toString() + "] to the database.");

			session.persist(objNewUser);
			tx.commit();
			return objNewUser;

		} catch (Exception e) {
			objLogger.error(sMethod + "Exception: cause: [" + e.getCause() + "] class name [" + e.getClass().getName()
					+ "] [" + e.toString() + "]");
			objLogger.error(sMethod + "Exception: toString: [" + e.toString() + " message: [" + e.getMessage() + "]");
			return null;
		} finally {
			session.close();
		}

	}

	//
	// ### check that user exists, do not allow userid, username, or password to
	// update
	@Override // 20210820 completed
	public User editRecord(AddOrEditDTO objAddOrEditDTO) throws SQLException {
		String sMethod = csCRT + "editRecord(): ";
		objLogger.trace(csCR + sMethod + "Entered");

		objLogger.debug(sMethod + "objAddOrEditDTO: [" + objAddOrEditDTO.toString() + "]");

		// get UserRole object
		String sRoleName = objAddOrEditDTO.getDataElement(csEmployeeRolesTblEmployeeRole);
		EmployeeRoleDAOImpl objUserRoleDAOImpl = new EmployeeRoleDAOImpl();
		EmployeeRole objUserRole = objUserRoleDAOImpl.getByRecordIdentifer(sRoleName);

		// by this time the service layer would have validated the parameters
		String sUsername = objAddOrEditDTO.getDataElement(csUserTblUsername);

		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		try {

			String sHQL = "";
			sHQL = "FROM User u WHERE u.username = :username";
			objLogger.debug(sMethod + "sHQL: [" + sHQL + "]" + " param: sUsername: [" + sUsername + "]");

			// need to get user object to edit here and not through class method
			// getByRecordIdentifer()
			// due to the way sessions are open/closed within each methods. When using the
			// other class
			// method we loose connection to session object and cannot update.
			User objUserToEdit = (User) session.createQuery(sHQL).setParameter("username", sUsername).getSingleResult();

			// will not update the record id, the company username, or the company email.
			// also password should be updated through separate process where old pwd and
			// new pwd are entered
			// set user object with
			String sFirstName = objAddOrEditDTO.getDataElement(csUserTblFirstName);
			String sLastName = objAddOrEditDTO.getDataElement(csUsrTblLastName);

			// The only fields allowed to change
			objUserToEdit.setFirstName(sFirstName);
			objUserToEdit.setLastName(sLastName);
			objUserToEdit.setEmployeeRole(objUserRole);

			objLogger.debug(sMethod + "DB update with objUserToEdit: [" + objUserToEdit.toString() + "]");

			session.persist(objUserToEdit);
			tx.commit();

			return objUserToEdit;

		} catch (Exception e) {
			objLogger.error(sMethod + "Exception: cause: [" + e.getCause() + "] class name [" + e.getClass().getName()
					+ "] [" + e.toString() + "]");
			objLogger.error(sMethod + "Exception: toString: [" + e.toString() + " message: [" + e.getMessage() + "]");
			return null;
		} finally {
			session.close();
		}
	}


	@Override
	public User getLogin(String sUsername, String sPassword) throws SQLException {
		String sMethod = csCRT + "getLogin(): ";
		objLogger.trace(csCR + sMethod + "Entered");

		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();

		try {
			objLogger.debug(sMethod + "Authenticating username: [" + sUsername + "] with password provided.");
			String sHQL = "FROM User u WHERE u.username = :username AND u.password = :password";
			// can no longer do a direct compare in the database
			// need to retrieve the record with password and salt then validate through
			// password utility.
			sHQL = "FROM User u WHERE u.username = :username";

			objLogger.debug(sMethod + "sHQL: [" + sHQL + "] with parameter username: [" + sUsername + "]");

			User objUser = (User) session.createQuery(sHQL).setParameter("username", sUsername).getSingleResult();

			objLogger.debug(sMethod + "found user: [" + objUser.toString() + "] in database now validate password.");
			String sEncryptedPwd = objUser.getPassword();
			String sSalt = objUser.getPasswordSalt();
			boolean bPasswordValid = PasswordUtil.verifyUserPassword(sPassword, sEncryptedPwd, sSalt);

			if (bPasswordValid) {
				objLogger.debug(sMethod + "user: [" + objUser.toString() + "]" + csCRT + "DID pass encrypted password validation with salt key.");
				return objUser;
			} else {
				objLogger.warn(sMethod + "user: [" + objUser.toString() + "]" + csCRT + "DID NOT pass encrypted password validation with salt key.");
				return null;
			}

		} catch (NoResultException e) {
			objLogger.debug(sMethod + "NoResultException: [" + e.getMessage() + "]");
			return null;

		} finally {
			session.close();
		}
	}


}
