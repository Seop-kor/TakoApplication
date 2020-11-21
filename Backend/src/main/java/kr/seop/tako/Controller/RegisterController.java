package kr.seop.tako.Controller;

import kr.seop.tako.DTO.MemberDTO;
import kr.seop.tako.Service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class RegisterController {
    private MemberService memberService;

    @GetMapping("/idcheck/{id}")
    public boolean idcheck(@PathVariable("id")String id){
        int count = memberService.countByUserid(id);
        if(count > 0){
            return true;
        }
        return false;
    }

    @PostMapping("/register")
    public void register(MemberDTO memberDTO){
        memberService.save(memberDTO);
    }
}
