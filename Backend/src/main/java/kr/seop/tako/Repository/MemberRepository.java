package kr.seop.tako.Repository;

import kr.seop.tako.DTO.MemberDTO;
import kr.seop.tako.Entity.MemberEntity;
import kr.seop.tako.Entity.PK.MemberPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<MemberEntity, MemberPK> {
    int countByUserid(String userid);
    MemberEntity findByUserid(String userid);
    int countByToken(String token);

    @Query(value = "update member set address=:address where token=:token", nativeQuery = true)
    void saveByToken(@Param("address") String address,@Param("token") String token);

    @Query(value = "update member set address=null where token=:token", nativeQuery = true)
    void deleteByToken(@Param("token")String token);
}
