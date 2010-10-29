package ikriti.natgeo.dao;

import ikriti.natgeo.dao.interfaces.FbUserDAO;
import ikriti.natgeo.dao.interfaces.MemberDAO;
import ikriti.natgeo.hb.FbUser;
import ikriti.natgeo.hb.Member;

import org.springframework.stereotype.Repository;

/**
 * <p>
 * The @Repository allows Spring to recognize this as a managed component (so we
 * don't need to specify it in XML) and also tells spring to do DAO exception
 * translation to the Spring exception hierarchy.
 * 
 * 
 */
@Repository
public class FbUserDAOImpl extends BaseDAO<FbUser, Long> implements FbUserDAO
{

}
