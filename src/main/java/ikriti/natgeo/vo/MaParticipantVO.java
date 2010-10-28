package ikriti.natgeo.vo;

public class MaParticipantVO
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
	public String getHeight()
	{
		return height;
	}
	public void setHeight(String height)
	{
		this.height = height;
	}
	public String getWeight()
	{
		return weight;
	}
	public void setWeight(String weight)
	{
		this.weight = weight;
	}
	public String getAge()
	{
		return age;
	}
	public void setAge(String age)
	{
		this.age = age;
	}
	private int id;
    private MemberVO member;
    private String height;
    private String weight;
    private String age;
}
