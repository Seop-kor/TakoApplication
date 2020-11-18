package kr.seop.tako.Controller;

import kr.seop.tako.DTO.MemberDTO;
import kr.seop.tako.Entity.MemberEntity;
import kr.seop.tako.Service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin
public class AddressController {
    private MemberService service;

    @GetMapping("/address")
    public Map<String, Object> getaddress(){
        Map<String, Object> a = new HashMap<>();
        Map<String, String> addressstat = new HashMap<>();
        Map<String, String> resultstat_a = new HashMap<>();

        List<MemberEntity> list = service.findByAll();

        if(list.size() > 0)
            resultstat_a.put("code","0");
        else{
            resultstat_a.put("code", "1");
        }
        a.put("result", resultstat_a);

        for(MemberEntity m : list){
            addressstat.put("address", m.getAddress());
            addressstat.put("phone", m.getPhone());
            a.put(String.valueOf(m.getMemberPK().getMemberid()), addressstat);
        }
        return a;
    }

    @PostMapping("/address")
    public boolean setaddress(MemberDTO memberDTO) {
        service.addressadd(memberDTO);
        return true;
    }

    @PostMapping("/del_address")
    public boolean deleteaddress(String token){
        service.deleteByToken(token);
        return true;
    }
}
