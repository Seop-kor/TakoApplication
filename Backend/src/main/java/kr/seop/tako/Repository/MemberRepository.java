package kr.seop.tako.Repository;

import kr.seop.tako.DTO.MemberDTO;
import kr.seop.tako.Entity.MemberEntity;
import kr.seop.tako.Entity.PK.MemberPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<MemberEntity, MemberPK> {
    int countByUserid(String userid);
    MemberEntity findByUserid(String userid);
    int countByToken(String token);
    List<MemberEntity> findAllByLatNotNullAndLonNotNullAndAddressNotNull();

    @Query(value = "update member set address=:address,lat=:lat,lon=:lon where token=:token", nativeQuery = true)
    void saveByToken(@Param("address") String address,@Param("lat") Double lat, @Param("lon") Double lon, @Param("token") String token);

    @Query(value = "update member set address=null,lat=null,lon=null where token=:token", nativeQuery = true)
    void deleteByToken(@Param("token")String token);

    @Query(value = "update member set token=:token where userid=:userid", nativeQuery = true)
    void tokenUpdateByUserid(@Param("token")String token, @Param("userid")String userid);
}
