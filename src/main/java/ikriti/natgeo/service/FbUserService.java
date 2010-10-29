package ikriti.natgeo.service;

import ikriti.natgeo.dao.interfaces.FbUserDAO;
import ikriti.natgeo.dao.interfaces.MemberDAO;
import ikriti.natgeo.hb.FbUser;
import ikriti.natgeo.hb.Member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trg.search.ISearch;
import com.trg.search.Search;
import com.trg.search.SearchResult;

@Service
public class FbUserService
{

	FbUserDAO dao;

	@Autowired
	public void setDao(FbUserDAO dao)
	{
		this.dao = dao;
	}

	public FbUserDAO getDao()
	{
		return this.dao;
	}

	public void save(FbUser fbUser)
	{
		dao.save(fbUser);
	}

	public List<FbUser> findAll()
	{
		return dao.findAll();
	}

	public void flush()
	{
		dao.flush();
	}

	public List<FbUser> search(ISearch search)
	{
		return dao.search(search);
	}

	public FbUser searchUnique(ISearch search)
	{
		return dao.searchUnique(search);
	}

	public FbUser findById(Long id)
	{
		return dao.find(id);
	}

	public void delete(Long id)
	{
		dao.removeById(id);
	}

	public SearchResult<FbUser> searchAndCount(ISearch search)
	{
		return dao.searchAndCount(search);
	}
}
