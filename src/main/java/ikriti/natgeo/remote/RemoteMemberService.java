package ikriti.natgeo.remote;

import ikriti.natgeo.constants.MemberStatus;
import ikriti.natgeo.hb.EnumMemberStatus;
import ikriti.natgeo.hb.FbUser;
import ikriti.natgeo.hb.Member;
import ikriti.natgeo.service.FbUserService;
import ikriti.natgeo.service.MaParticipantService;
import ikriti.natgeo.service.MemberService;
import ikriti.natgeo.vo.FbUserVO;
import ikriti.natgeo.vo.MaParticipantVO;
import ikriti.natgeo.vo.MemberVO;

import org.springframework.beans.factory.annotation.Autowired;

import com.trg.search.Search;

public class RemoteMemberService extends BaseService
{

	MemberService memberService;
	FbUserService fbUserService;
	MaParticipantService maParticipantService;
	
	@Autowired
	public void setMemberService(MemberService memberService)
	{
		this.memberService = memberService;
	}

	@Autowired
	public void setFbUserService(FbUserService fbUserService)
	{
		this.fbUserService = fbUserService;
	}

	@Autowired
	public void setMaParticipantService(MaParticipantService maParticipantService)
	{
		this.maParticipantService = maParticipantService;
	}

	public MemberVO register(MemberVO member)
	{
		Search search = new Search();
		return member;
	}
	
	public MaParticipantVO registerMissionArmyParticipant(MaParticipantVO ma)
	{
		Search search = new Search();
		return ma;
	}
	
	public MaParticipantVO getMissionArmyParticipantByMember(MemberVO member)
	{
		/*
		 * 1. find whether member has a corresponding entry in MissionArmyParticipant
		 * 2. return MaParticipantVO of the corresponding member
		 */
		Search search = new Search();
		return new MaParticipantVO();
	}

	public FbUserVO associateFB(FbUserVO fbUserVO)
	{
		/*
		 * 1. Get facebookId
		 * 2. Check whether facebookId has corresponding member
		 * 3. If not, create a member associating the facebook and the member
		 * 4. If yes, populate the memberVO with details from the database
		 * 4. return fbuser
		 */
		Search search = new Search();
		
		search.addFilterEqual("facebookId", fbUserVO.getFacebookId());
		FbUser fbUser = fbUserService.searchUnique(search);

		MemberVO memberVO = fbUserVO.getMember();
		
		System.out.println("RemoteMemberService.associateFB : fbUser = "+ fbUser);
		
		if(fbUser == null)
		{
			System.out.println("RemoteMemberService.associateFB : fbUser is null sdfsdfsadf ! = "+ fbUser);
			
			Member member = new Member();
			member.setFirstname(memberVO.getFirstname());
			member.setPhotoUrl(memberVO.getPhotoUrl());
			
			EnumMemberStatus memberStatus = new EnumMemberStatus();
			memberStatus.setId(MemberStatus.ACTIVE);
			member.setEnumMemberStatus(memberStatus);
			
			memberService.save(member);
			System.out.println("RemoteMemberService.associateFB : member saved "+ member);

			fbUser = new FbUser();
			
			fbUser.setMember(member);
			fbUser.setFacebookId(fbUserVO.getFacebookId());
			fbUser.setPhotoUrl(fbUserVO.getPhotoUrl());
			
			fbUserService.save(fbUser);
			
			memberVO.setId(member.getId());
			fbUserVO.setId(fbUser.getId());

		}
		else
		{
			System.out.println("RemoteMemberService.associateFB : fbUser.getId() = "+ fbUser.getId());
			Member member = fbUser.getMember();
			memberVO.setFirstname(member.getFirstname());
			memberVO.setPhotoUrl(member.getPhotoUrl());
			
			fbUserVO.setPhotoUrl(fbUser.getPhotoUrl());
		}

		System.out.println("RemoteMemberService.associateFB : fbUserVO = "+ fbUserVO);

		return fbUserVO;
	}
	
	public FbUserVO testAssociateFB()
	{
		
		FbUserVO fbUserVO = new FbUserVO();
		fbUserVO.setFacebookId("2");
		fbUserVO.setPhotoUrl("my_photo_url");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setFirstname("Rajesh Ghosh");
		memberVO.setPhotoUrl(fbUserVO.getPhotoUrl());
		
		fbUserVO.setMember(memberVO);
		
		fbUserVO = associateFB(fbUserVO);
		
		return fbUserVO;
	}
	
}
