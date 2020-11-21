package kr.seop.tako.Service;

import kr.seop.tako.Entity.MemberEntity;
import kr.seop.tako.Repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private MemberRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //여기에 이제 DB를 연결해서 id를 받아 쿼리문을 작성후 간다. username과 password를 반환
        MemberEntity memberEntity = repository.findByUserid(s);
//        MemberVO vo= dao.select(s);
        if(memberEntity.getUserpass() != null){
            return new User(s,memberEntity.getUserpass(), new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("User not found with username:"+s);
        }
    }

}
