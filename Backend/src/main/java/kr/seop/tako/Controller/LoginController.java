package kr.seop.tako.Controller;

import kr.seop.tako.Config.JwtTokenUtil;
import kr.seop.tako.DTO.MemberDTO;
import kr.seop.tako.Service.JwtUserDetailsService;
import kr.seop.tako.Service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@AllArgsConstructor
@CrossOrigin
public class LoginController {
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private JwtUserDetailsService userDetailsService;
    private MemberService memberService;

    @PostMapping("/login")
    public String login(MemberDTO memberDTO) throws Exception{

        //1. 우선 토큰이 만료되었는지 확인 - > 토큰

        if(memberDTO.getToken() == null){
            if(memberService.findByUserid(memberDTO)) {
                authenticate(memberDTO.getUserid(), memberDTO.getUserpass());
                final UserDetails userDetails = userDetailsService.loadUserByUsername(memberDTO.getUserid());
                final String token = jwtTokenUtil.generateToken(userDetails);
                memberService.tokenUpdateByUserid(memberDTO, token);
                return token;
            }
            else
                return "null";
        }else{
            if(jwtTokenUtil.getExpirationDateFromToken(memberDTO.getToken()).before(new Date())){
                int stat = memberService.countByUserid(jwtTokenUtil.getUsernameFromToken(memberDTO.getToken()));
                if(stat > 0){
                    return "true";
                }else{
                    return "null";
                }
            }else{
                return "null";
            }
        }
    }

    private void authenticate(String username, String password) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException e){
            throw new Exception("user_Disabled", e);
        }catch (BadCredentialsException e){
            throw new Exception("invalid_credentials",e);
        }
    }
}
