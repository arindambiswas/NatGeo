package ikriti.natgeo.service;

import ikriti.natgeo.dao.interfaces.MaParticipantDAO;
import ikriti.natgeo.hb.MaParticipant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trg.search.ISearch;
import com.trg.search.SearchResult;

@Service
public class MaParticipantService
{

	MaParticipantDAO dao;

	@Autowired
	public void setDao(MaParticipantDAO dao)
	{
		this.dao = dao;
	}

	public MaParticipantDAO getDao()
	{
		return this.dao;
	}

	public void save(MaParticipant maParticipant)
	{
		dao.save(maParticipant);
	}

	public List<MaParticipant> findAll()
	{
		return dao.findAll();
	}

	public void flush()
	{
		dao.flush();
	}

	public List<MaParticipant> search(ISearch search)
	{
		return dao.search(search);
	}

	public MaParticipant searchUnique(ISearch search)
	{
		return dao.searchUnique(search);
	}

	public MaParticipant findById(Long id)
	{
		return dao.find(id);
	}

	public void delete(Long id)
	{
		dao.removeById(id);
	}

	public SearchResult<MaParticipant> searchAndCount(ISearch search)
	{
		return dao.searchAndCount(search);
	}
}
