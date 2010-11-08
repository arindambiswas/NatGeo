package ikriti.natgeo.hb;
// Generated Nov 7, 2010 11:25:27 PM by Hibernate Tools 3.2.2.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * EnumGender generated by hbm2java
 */
@Entity
@Table(name="enum_gender"
    ,schema="public"
)
public class EnumGender  implements java.io.Serializable {


     private int id;
     private String gender;
     private String description;
     private Set<Member> members = new HashSet<Member>(0);

    public EnumGender() {
    }

	
    public EnumGender(String gender) {
        this.gender = gender;
    }
    public EnumGender(String gender, String description, Set<Member> members) {
       this.gender = gender;
       this.description = description;
       this.members = members;
    }
   
     @SequenceGenerator(name="generator", allocationSize=1, sequenceName="enum_gender_id_seq")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")
    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    @Column(name="gender", nullable=false, length=20)
    public String getGender() {
        return this.gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    @Column(name="description")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="enumGender")
    public Set<Member> getMembers() {
        return this.members;
    }
    
    public void setMembers(Set<Member> members) {
        this.members = members;
    }




}


