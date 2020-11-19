package kr.seop.tako.Controller;

import kr.seop.tako.DTO.MemberDTO;
import kr.seop.tako.Entity.MemberEntity;
import kr.seop.tako.Service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin
public class AddressController {
    private MemberService service;

    @GetMapping("/address/{lat}/{lon}")
    public Map<String, Object> getaddresslatlon(@PathVariable(value = "lat") double lat, @PathVariable(value = "lon") double lon){
        List<Object> obj  = new ArrayList<>();
        Map<String, Object> a = new HashMap<>();
        HashMap<String, String> addressstat = new HashMap<>();
        Map<String, String> resultstat_a = new HashMap<>();

        List<MemberEntity> list = service.findByAll();

        if(list.size() > 0)
            resultstat_a.put("code","0");
        else{
            resultstat_a.put("code", "1");
        }
        a.put("result", resultstat_a);

        if(list.size() > 0){
            for(MemberEntity m : list){
                if(distance(lat, lon, m.getLat(), m.getLon()) <= 1.0){
                    addressstat.put("lat", m.getLat().toString());
                    addressstat.put("lon", m.getLon().toString());
                    addressstat.put("address", m.getAddress());
                    addressstat.put("phone", m.getPhone());
                    obj.add(addressstat.clone());
                }
            }
            a.put("data", obj);
        }
        return a;
    }


//    @GetMapping("/address/{mylat}/{mylon}/{lat}/{lon}")
//    public Map<String, Object> getaddress(
//            @PathVariable("mylat") double mylat
//            , @PathVariable("mylon") double mylon
//            , @PathVariable("lat") double lat
//            , @PathVariable("lon") double lon
//    ){
//        List<Object> obj  = new ArrayList<>();
//        Map<String, Object> a = new HashMap<>();
//        HashMap<String, String> addressstat = new HashMap<>();
//        Map<String, String> resultstat_a = new HashMap<>();
//
//        List<MemberEntity> list = service.findByAll();
//
//        if(list.size() > 0)
//            resultstat_a.put("code","0");
//        else{
//            resultstat_a.put("code", "1");
//        }
//        a.put("result", resultstat_a);
//
//        if(list.size() > 0){
//            for(MemberEntity m : list){
//                addressstat.put("lat", m.getLat().toString());
//                addressstat.put("lon", m.getLon().toString());
//                addressstat.put("address", m.getAddress());
//                addressstat.put("phone", m.getPhone());
//                obj.add(addressstat.clone());
//            }
//
//            a.put("data", obj);
//        }
//        return a;
//    }

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


    private double distance(double lat1, double lon1, double lat2, double lon2) {

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515 * 1.609344;

        return (dist);
    }


    // This function converts decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

}
