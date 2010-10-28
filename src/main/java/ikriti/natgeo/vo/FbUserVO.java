package ikriti.natgeo.vo;

import java.util.Date;

public class FbUserVO
{

    public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public MemberVO getMember()
	{
		return member;
	}
	public void setMember(MemberVO member)
	{
		this.member = member;
	}
	public String getPhotoUrl()
	{
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl)
	{
		this.photoUrl = photoUrl;
	}
	public Date getCreateDate()
	{
		return createDate;
	}
	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}
	public String getAccessToken()
	{
		return accessToken;
	}
	public void setAccessToken(String accessToken)
	{
		this.accessToken = accessToken;
	}
	public Boolean getIsValidAccessToken()
	{
		return isValidAccessToken;
	}
	public void setIsValidAccessToken(Boolean isValidAccessToken)
	{
		this.isValidAccessToken = isValidAccessToken;
	}
	public String getFacebookId()
	{
		return facebookId;
	}
	public void setFacebookId(String facebookId)
	{
		this.facebookId = facebookId;
	}
	private int id;
    private MemberVO member;
    private String photoUrl;
    private Date createDate;
    private String accessToken;
    private Boolean isValidAccessToken;
    private String facebookId;
}
