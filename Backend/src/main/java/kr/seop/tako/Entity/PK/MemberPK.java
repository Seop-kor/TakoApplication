package kr.seop.tako.Entity.PK;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Embeddable
public class MemberPK implements Serializable {
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberid;

    public MemberPK(int memberid){
        this.memberid = memberid;
    }
}
