package ikriti.natgeo.hb;
// Generated Oct 29, 2010 12:55:50 AM by Hibernate Tools 3.2.2.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Member generated by hbm2java
 */
@Entity
@Table(name="member"
    ,schema="public"
)
public class Member  implements java.io.Serializable {


     private int id;
     private EnumGender enumGender;
     private EnumMemberStatus enumMemberStatus;
     private String guid;
     private String firstname;
     private String lastname;
     private Date dob;
     private String email;
     private String mobile;
     private String photoUrl;
     private String privateKey;
     private Date createDate;
     private Set<MaParticipant> maParticipants = new HashSet<MaParticipant>(0);
     private Set<FbUser> fbUsers = new HashSet<FbUser>(0);

    public Member() {
    }

    public Member(EnumGender enumGender, EnumMemberStatus enumMemberStatus, String guid, String firstname, String lastname, Date dob, String email, String mobile, String photoUrl, String privateKey, Date createDate, Set<MaParticipant> maParticipants, Set<FbUser> fbUsers) {
       this.enumGender = enumGender;
       this.enumMemberStatus = enumMemberStatus;
       this.guid = guid;
       this.firstname = firstname;
       this.lastname = lastname;
       this.dob = dob;
       this.email = email;
       this.mobile = mobile;
       this.photoUrl = photoUrl;
       this.privateKey = privateKey;
       this.createDate = createDate;
       this.maParticipants = maParticipants;
       this.fbUsers = fbUsers;
    }
   
     @SequenceGenerator(name="generator", allocationSize=1, sequenceName="member_id_seq")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")
    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="gender")
    public EnumGender getEnumGender() {
        return this.enumGender;
    }
    
    public void setEnumGender(EnumGender enumGender) {
        this.enumGender = enumGender;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="member_status")
    public EnumMemberStatus getEnumMemberStatus() {
        return this.enumMemberStatus;
    }
    
    public void setEnumMemberStatus(EnumMemberStatus enumMemberStatus) {
        this.enumMemberStatus = enumMemberStatus;
    }
    
    @Column(name="guid")
    public String getGuid() {
        return this.guid;
    }
    
    public void setGuid(String guid) {
        this.guid = guid;
    }
    
    @Column(name="firstname")
    public String getFirstname() {
        return this.firstname;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
    @Column(name="lastname")
    public String getLastname() {
        return this.lastname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="dob", length=13)
    public Date getDob() {
        return this.dob;
    }
    
    public void setDob(Date dob) {
        this.dob = dob;
    }
    
    @Column(name="email")
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name="mobile")
    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    @Column(name="photo_url", length=500)
    public String getPhotoUrl() {
        return this.photoUrl;
    }
    
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
    
    @Column(name="private_key")
    public String getPrivateKey() {
        return this.privateKey;
    }
    
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date", length=29)
    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="member")
    public Set<MaParticipant> getMaParticipants() {
        return this.maParticipants;
    }
    
    public void setMaParticipants(Set<MaParticipant> maParticipants) {
        this.maParticipants = maParticipants;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="member")
    public Set<FbUser> getFbUsers() {
        return this.fbUsers;
    }
    
    public void setFbUsers(Set<FbUser> fbUsers) {
        this.fbUsers = fbUsers;
    }




}


