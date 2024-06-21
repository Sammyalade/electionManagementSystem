package com.system.ElectionManagement.services.impl;

import com.cloudinary.Cloudinary;
import com.system.ElectionManagement.dtos.requests.CandidateRequest;
import com.system.ElectionManagement.dtos.responses.CandidateResponse;
import com.system.ElectionManagement.services.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {


    private final Cloudinary cloudinary;


    @Override
    public CandidateResponse registerCandidate(CandidateRequest candidateRequest) {
        try {
            Map<?,?> response = cloudinary.uploader().upload(uploadRequest.getFile().getBytes(), new HashMap());
            String url = response.get("url").toString();
            Media media=mapper.map(uploadRequest,Media.class);
            media.setUrl(url);
            media.setUploader(user);
            media = mediaRepo.save(media);
            return mapper.map(media, MediaResponse.class);
        } catch (IOException exception) {
            exception.printStackTrace();
            throw new RuntimeException("something went wrong");
        }
    }
}
