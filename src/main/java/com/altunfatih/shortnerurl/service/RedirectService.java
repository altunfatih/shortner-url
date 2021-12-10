package com.altunfatih.shortnerurl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altunfatih.shortnerurl.entity.Redirect;
import com.altunfatih.shortnerurl.exception.BadRequestException;
import com.altunfatih.shortnerurl.exception.NotFoundException;
import com.altunfatih.shortnerurl.repository.RedirectRepository;
import com.altunfatih.shortnerurl.request.RedirectCreationRequest;

import java.util.Optional;

@Service
public class RedirectService {

    private RedirectRepository redirectRepository;

    @Autowired
    public RedirectService(RedirectRepository redirectRepository) {
        this.redirectRepository = redirectRepository;
    }

    public Optional<Redirect> createRedirect(RedirectCreationRequest redirectCreationRequest) {
        if (redirectRepository.existsByAlias(redirectCreationRequest.getAlias())) {
            throw new BadRequestException("Alias already exists");
        }
        System.out.println("Redirect Request " + redirectCreationRequest.toString());
        Redirect redirect = new Redirect(redirectCreationRequest.getAlias(), redirectCreationRequest.getUrl());

        Redirect postSaveRedirect = redirectRepository.save(redirect);
        System.out.println("Redirect" + postSaveRedirect);


        return Optional.ofNullable(postSaveRedirect);
    }

    public Redirect getRedirect(String alias) {
        Redirect redirect = redirectRepository.findByAlias(alias)
                .orElseThrow(() -> new NotFoundException("Hey we don't have that alias ! Try making it"));
        return redirect;
    }

}