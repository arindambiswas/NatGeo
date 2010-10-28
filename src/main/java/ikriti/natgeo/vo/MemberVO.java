package ikriti.natgeo.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberVO
{
    public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getGuid()
	{
		return guid;
	}
	public void setGuid(String guid)
	{
		this.guid = guid;
	}
	public String getFirstname()
	{
		return firstname;
	}
	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}
	public String getLastname()
	{
		return lastname;
	}
	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}
	public Date getDob()
	{
		return dob;
	}
	public void setDob(Date dob)
	{
		this.dob = dob;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getMobile()
	{
		return mobile;
	}
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}
	public EnumGenderVO getGender()
	{
		return gender;
	}
	public void setGender(EnumGenderVO gender)
	{
		this.gender = gender;
	}
	public String getPhotoUrl()
	{
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl)
	{
		this.photoUrl = photoUrl;
	}
	public String getPrivateKey()
	{
		return privateKey;
	}
	public void setPrivateKey(String privateKey)
	{
		this.privateKey = privateKey;
	}
	public Date getCreateDate()
	{
		return createDate;
	}
	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}
	public EnumMemberStatusVO getMemberStatus()
	{
		return memberStatus;
	}
	public void setMemberStatus(EnumMemberStatusVO memberStatus)
	{
		this.memberStatus = memberStatus;
	}
	public List<FbUserVO> getFbUsers()
	{
		return fbUsers;
	}
	public void setFbUsers(List<FbUserVO> fbUsers)
	{
		this.fbUsers = fbUsers;
	}

	private int id;
    private String guid;
    private String firstname;
    private String lastname;
    private Date dob;
    private String email;
    private String mobile;
    private EnumGenderVO gender;
    private String photoUrl;
    private String privateKey;
    private Date createDate;
    private EnumMemberStatusVO memberStatus;
    private List<FbUserVO> fbUsers = new ArrayList<FbUserVO>(0);
	
}
