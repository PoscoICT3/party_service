package com.example.partyService.party.service;

import com.example.partyService.party.model.PartyDto;

import java.util.List;

public interface PartyService {
    Integer registerParty(PartyDto PartyDto);

    List<PartyDto> findAll();
}
