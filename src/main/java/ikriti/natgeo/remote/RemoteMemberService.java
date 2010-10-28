package ikriti.natgeo.remote;

import ikriti.natgeo.service.MemberService;
import ikriti.natgeo.vo.FbUserVO;
import ikriti.natgeo.vo.MaParticipantVO;
import ikriti.natgeo.vo.MemberVO;

import org.springframework.beans.factory.annotation.Autowired;

import com.trg.search.Search;

public class RemoteMemberService extends BaseService
{

	MemberService memberService;
	
	@Autowired
	public void setMemberService(MemberService memberService)
	{
		this.memberService = memberService;
	}

	public MemberVO register(MemberVO member)
	{
		Search search = new Search();
		/*
		Member member = null;
		try
		{
			search.addFilterEqual("id", "-1");
			member = memberService.searchUnique(search);
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		*/
		return member;
	}
	
	public MaParticipantVO registerMissionArmyParticipant(MaParticipantVO ma)
	{
		Search search = new Search();
		return ma;
	}
	
	public FbUserVO associateFB(FbUserVO fbuser)
	{
		Search search = new Search();
		return fbuser;
	}
}
