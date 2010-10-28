package ikriti.natgeo.remote;

import ikriti.natgeo.hb.Member;
import ikriti.natgeo.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;

public class RemoteMemberService extends BaseService
{

	MemberService memberService;
	
	@Autowired
	public void setMemberService(MemberService memberService)
	{
		this.memberService = memberService;
	}

	public Member register(Member member)
	{
		member.setId(23);
		
		return member;
	}
}
