package ikriti.natgeo.service;

import ikriti.natgeo.dao.interfaces.MemberDAO;
import ikriti.natgeo.hb.Member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trg.search.ISearch;
import com.trg.search.Search;
import com.trg.search.SearchResult;

@Service
public class MemberService
{

	MemberDAO dao;

	@Autowired
	public void setDao(MemberDAO dao)
	{
		this.dao = dao;
	}

	public MemberDAO getDao()
	{
		return this.dao;
	}

	public void save(Member member)
	{
		dao.save(member);
	}

	public List<Member> findAll()
	{
		return dao.findAll();
	}

	public Member findByName(String name)
	{
		if (name == null) return null;
		return dao.searchUnique(new Search().addFilterEqual("name", name));
	}

	public void flush()
	{
		dao.flush();
	}

	public List<Member> search(ISearch search)
	{
		return dao.search(search);
	}

	public Member searchUnique(ISearch search)
	{
		return dao.searchUnique(search);
	}

	public Member findById(Long id)
	{
		return dao.find(id);
	}

	public Member findByGuid(String guid)
	{
		if (guid == null) return null;
		return dao.searchUnique(new Search().addFilterEqual("guid", guid));
	}

	public void delete(Long id)
	{
		dao.removeById(id);
	}

	public SearchResult<Member> searchAndCount(ISearch search)
	{
		return dao.searchAndCount(search);
	}
}
