package com.example.partyService.party.repository;




import com.example.partyService.party.model.PartyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PartyMapper {

    Integer registerParty(PartyDto partyDto);

    List<PartyDto> findAll();
}
