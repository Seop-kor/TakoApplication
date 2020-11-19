package kr.seop.tako.Service;

import kr.seop.tako.DTO.MemberDTO;
import kr.seop.tako.Entity.MemberEntity;
import kr.seop.tako.Repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Log
public class MemberService {
    private MemberRepository repository;
    private PasswordEncoder encoder;

    public boolean findByUserid(MemberDTO memberDTO) {
        MemberEntity memberEntity = repository.findByUserid(memberDTO.getUserid());
        if (memberEntity == null) {
            return false;
        }

        if (encoder.matches(memberDTO.getUserpass(), memberEntity.getUserpass())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean countByToken(MemberDTO memberDTO) {
        if (repository.countByToken(memberDTO.getToken()) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void save(MemberDTO memberDTO) {
        memberDTO.setUserpass(encoder.encode(memberDTO.getUserpass()));
        repository.save(memberDTO.toEntity());
    }

    public void addressadd(MemberDTO memberDTO){
        repository.saveByToken(memberDTO.getAddress(), memberDTO.getLat(), memberDTO.getLon(), memberDTO.getToken());
    }


    public int countByUserid(String userid) {
        return repository.countByUserid(userid);
    }

    public List<MemberEntity> findByAll() {
        return repository.findAllByLatNotNullAndLonNotNullAndAddressNotNull();
    }

    public void deleteByToken(String token){
        repository.deleteByToken(token);
    }

    public void tokenUpdateByUserid(MemberDTO memberDTO, String token){
        repository.tokenUpdateByUserid(token, memberDTO.getUserid());
    }
}
