package com.example.partyService.party.service;
import com.example.partyService.party.model.PartyDto;
import com.example.partyService.party.repository.PartyMapper;
import com.example.partyService.resttemplate.dto.UserResponseDto;
import com.example.partyService.resttemplate.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class PartyServiceImpl implements PartyService{

        @Autowired
        PartyMapper partyMapper;

        @Autowired
        RestTemplateService restTemplateService;

        @Override
        public Integer registerParty(PartyDto partyDto) {
            return partyMapper.registerParty(partyDto);
        }

        @Override
        public List<PartyDto> findAll() {

                List<PartyDto> partydtos = partyMapper.findAll();
                System.out.println(partydtos);

                for(PartyDto x : partydtos){
                        URI uri = UriComponentsBuilder
                                .fromUriString("http://localhost:8000")
                                .path("/user/" + x.getId())
                                .encode()
                                .build()
                                .toUri();

                        // user에 있는 id와 party에 있는 userId와 같은걸 찾아야함
                        // 먼저 usertable에 던진다.

                        // uri
                        System.out.println(uri.toString());

                        RestTemplate restTemplate = new RestTemplate();

                        UserResponseDto result2 = restTemplate.getForObject(uri,UserResponseDto.class);
                        System.out.println(result2);

                        x.setName(result2.getName());

                }

                System.out.println("최종 ------------------- ");
                System.out.println(partydtos);






//                ResponseEntity<UserResponseDto[]> result = restTemplate.getForEntity(uri, UserResponseDto[].class);
//                UserResponseDto[] array = result.getBody();

//                for(UserResponseDto dto : array){
//                        System.out.println(dto.getId());
//                        System.out.println(dto);
//
//                }

//                ResponseEntity<ShopRateDto[]> result = restTemplate.getForEntity(uri, ShopRateDto[].class);
//                ShopRateDto[] array = result.getBody();
//
//
//                for(ShopRateDto dto : array){
//                        System.out.println(dto.getShopId());
//                        System.out.println(dto.getRate());
//                }

//                System.out.println(result);

                return partydtos;
        }

       // public List<PartyDto> findAll() {
                //return partyMapper.findAll();
       // }
}
