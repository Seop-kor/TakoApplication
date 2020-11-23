package kr.seop.tako.Entity;

import kr.seop.tako.Entity.PK.MemberPK;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "member")
public class MemberEntity {
    @EmbeddedId
    private MemberPK memberPK;

    @Column(nullable = false)
    private String userid;

    @Column(nullable = false)
    private String userpass;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String mail;

    private String address;

    private Double lat;

    private Double lon;

    private String token;

    @Builder
    public MemberEntity(int memberid, String userid, String userpass, String address, String username, String phone, String mail
    ,Double lat, Double lon, String token){
        this.memberPK = new MemberPK(memberid);
        this.userid = userid;
        this.userpass = userpass;
        this.username = username;
        this.phone = phone;
        this.mail = mail;
        this.address = address;
        this.lat = lat;
        this.lon = lon;
        this.token = token;
    }

}
