package kr.seop.tako.DTO;

import kr.seop.tako.Entity.MemberEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MemberDTO {
    private int memberid;
    private String userid;
    private String userpass;
    private String username;
    private String phone;
    private String mail;
    private String address;
    private double lat;
    private double lon;
    private String token;

    public MemberEntity toEntity(){
        return MemberEntity.builder().memberid(memberid).userid(userid).userpass(userpass).username(username).phone(phone)
                .mail(mail).address(address).lat(lat).lon(lon).token(token).build();
    }
}
