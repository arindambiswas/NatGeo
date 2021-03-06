package ikriti.natgeo.dao.interfaces;

import ikriti.natgeo.hb.FbUser;
import ikriti.natgeo.hb.Member;

import com.trg.dao.hibernate.GenericDAO;

/**
 * <p>
 * As a matter of best practice other components reference this interface rather
 * than the implementation of the DAO itself.
 * 
 */
public interface FbUserDAO extends GenericDAO<FbUser, Long>
{

}
