package ikriti.natgeo.remote;

import ikriti.natgeo.hb.Member;
import ikriti.natgeo.service.MemberService;

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

	public Member register()
	{
		Search search = new Search();
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
		return member;
	}
}
