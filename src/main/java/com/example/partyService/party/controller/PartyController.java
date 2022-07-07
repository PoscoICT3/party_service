package com.example.partyService.party.controller;

import com.example.partyService.config.SecurityService;
import com.example.partyService.party.model.PartyDto;
import com.example.partyService.party.service.PartyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("party")
public class PartyController {

    @Autowired
    PartyServiceImpl partyService;

    @Autowired
    SecurityService securityService;


    @PostMapping("/create")
    public HashMap<String, Object> registerParty(@RequestBody PartyDto partyDto) {

        Integer userIdByToken = securityService.getIdAtToken();
        System.out.println(".................userId"+userIdByToken);
        System.out.println(".................party"+ partyDto);
        partyDto.setUserId(userIdByToken);
        HashMap<String, Object> map = new HashMap<String, Object>();

        LocalDate localDate = LocalDate.now();
        partyDto.setDate(String.valueOf(localDate));

        System.out.println(partyDto);

        Integer partyDtos = partyService.registerParty(partyDto);
        System.out.println("..............findallpary" + findAllParty());
        return findAllParty();
    }

    @GetMapping("/")
    public HashMap<String, Object> findAllParty() {

        HashMap<String, Object> map = new HashMap<String, Object>();
        List<PartyDto> partyDtos = partyService.findAll();
        System.out.println("findAll......"+partyDtos);
        map.put("partyusers", partyDtos);

        return map;
    }

}