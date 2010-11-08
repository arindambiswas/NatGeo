package ikriti.natgeo.remote;

import java.util.Date;

import ikriti.natgeo.constants.MemberStatus;
import ikriti.natgeo.hb.EnumGender;
import ikriti.natgeo.hb.EnumMemberStatus;
import ikriti.natgeo.hb.FbUser;
import ikriti.natgeo.hb.MaParticipant;
import ikriti.natgeo.hb.Member;
import ikriti.natgeo.service.FbUserService;
import ikriti.natgeo.service.MaParticipantService;
import ikriti.natgeo.service.MemberService;
import ikriti.natgeo.vo.EnumGenderVO;
import ikriti.natgeo.vo.EnumMemberStatusVO;
import ikriti.natgeo.vo.FbUserVO;
import ikriti.natgeo.vo.MaParticipantVO;
import ikriti.natgeo.vo.MemberVO;

import org.springframework.beans.factory.annotation.Autowired;

import com.collectivezen.util.mail.GoogleMail;
import com.collectivezen.util.mail.MailConfig;
import com.collectivezen.util.mail.MailContent;
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

	public MemberVO register(MemberVO memberVO)
	{
		Search search = new Search();

		return memberVO;
	}
	
	public MaParticipantVO registerMissionArmyParticipant(MaParticipantVO mapVO)
	{
		Search search = new Search();

		System.out.println("RemoteMemberService.registerMissionArmyParticipant : mapVO.getMember().getId() = "+ mapVO.getMember().getId());

		search.addFilterEqual("member.id", mapVO.getMember().getId());
		MaParticipant map = maParticipantService.searchUnique(search);
		System.out.println("RemoteMemberService.registerMissionArmyParticipant : map = "+ map);

		search.clear();
		search.addFilterEqual("id", mapVO.getMember().getId());
		Member member = memberService.searchUnique(search);
		System.out.println("RemoteMemberService.registerMissionArmyParticipant : member = "+ member);
		
		
		if(map == null)
		{
			// create a new mission army entrant
			map = new MaParticipant();
		}

		map.setAge(mapVO.getAge());
		map.setHeight(mapVO.getHeight());
		map.setWeight(mapVO.getWeight());

		MemberVO memberVO = mapVO.getMember();
		
		member.setEmail(memberVO.getEmail());
		member.setFirstname(memberVO.getFirstname());
		member.setLastname(memberVO.getLastname());
		member.setMobile(memberVO.getMobile());
		
		EnumGenderVO genderVO = memberVO.getGender();
		
		EnumGender gender = new EnumGender();
		gender.setId(genderVO.getId());
		
		member.setEnumGender(gender);
		map.setMember(member);
			
		maParticipantService.save(map);
		
		mapVO.setId(map.getId());
		
		String mailBody = "Hi "+ memberVO.getFirstname() +", <br /><br />";
		mailBody += "Thank you for participating in Idea Presents Nat Geo Mission Army. You have successfully registered. We will keep you informed about the next stage of the Mission shortly.<br />";
		mailBody += "Thanks, <br />";
		mailBody += "NatGeo India";
		
		MailContent mailContent = new MailContent("natgeo.feedback@gmail.com", memberVO.getEmail(), "Welcome to Idea Presents Nat Geo Mission Army!", mailBody);
		MailConfig mailConfig = new MailConfig("smtp.gmail.com", "natgeo.feedback@gmail.com", "kalkik4lk1");
		
		GoogleMail.sendMail(mailContent, mailConfig);
		
		return mapVO;
	}
	
	public MaParticipantVO getMissionArmyParticipantByMember(MemberVO memberVO)
	{
		/*
		 * 1. find whether member has a corresponding entry in MissionArmyParticipant
		 * 2. return MaParticipantVO of the corresponding member
		 */
		MaParticipantVO mapVO = new MaParticipantVO();

		Search search = new Search();
		search.addFilterEqual("member.id", memberVO.getId());
		MaParticipant map = maParticipantService.searchUnique(search);
		
		if(map != null)
		{
			mapVO.setAge(map.getAge());
			mapVO.setHeight(map.getHeight());
			mapVO.setId(map.getId());
			mapVO.setWeight(map.getWeight());
			
			Member member = map.getMember();
			
			memberVO.setCreateDate(member.getCreateDate());
			memberVO.setDob(member.getDob());
			memberVO.setEmail(member.getEmail());
			memberVO.setFirstname(member.getFirstname());
			memberVO.setLastname(member.getLastname());
			memberVO.setGuid(member.getGuid());
			memberVO.setId(member.getId());
			memberVO.setPhotoUrl(member.getPhotoUrl());
			memberVO.setMobile(member.getMobile());
			
			mapVO.setMember(memberVO);
		}
		return mapVO;
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
			System.out.println("RemoteMemberService.associateFB : fbUser is new, fbuser = "+ fbUser);
			
			Member member = new Member();
			member.setFirstname(memberVO.getFirstname());
			member.setPhotoUrl(fbUserVO.getPhotoUrl());
			member.setCreateDate(new Date());
			
			EnumMemberStatus memberStatus = new EnumMemberStatus();
			memberStatus.setId(MemberStatus.ACTIVE);
			member.setEnumMemberStatus(memberStatus);
			
			memberService.save(member);
			System.out.println("RemoteMemberService.associateFB : member saved "+ member);

			fbUser = new FbUser();
			
			fbUser.setMember(member);
			fbUser.setFacebookId(fbUserVO.getFacebookId());
			fbUser.setPhotoUrl(fbUserVO.getPhotoUrl());
			fbUser.setCreateDate(new Date());
			
			fbUserService.save(fbUser);
			
			memberVO.setId(member.getId());
			memberVO.setPhotoUrl(member.getPhotoUrl());
			fbUserVO.setId(fbUser.getId());

		}
		else
		{
			System.out.println("RemoteMemberService.associateFB : fbUser.getId() = "+ fbUser.getId());
			Member member = fbUser.getMember();
			
			memberVO.setFirstname(member.getFirstname());
			memberVO.setPhotoUrl(member.getPhotoUrl());
			memberVO.setId(member.getId());
			
			fbUserVO.setPhotoUrl(fbUser.getPhotoUrl());
			fbUserVO.setId(fbUser.getId());
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
