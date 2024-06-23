package com.system.ElectionManagement.services.impl;


import com.system.ElectionManagement.dtos.requests.CandidateRequest;
import com.system.ElectionManagement.models.ContactInformation;
import com.system.ElectionManagement.services.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AdminServiceImplTest {
    @Autowired
    private AdminService adminService;
@Test
    void testAdminRegisterCandidate() throws IOException {
    CandidateRequest candidateRequest = new CandidateRequest();
    Path path = Paths.get("C:\\Users\\DELL\\OneDrive\\Documents\\Object oriented design.pdf");
    InputStream inputStream = Files.newInputStream(path);
    MultipartFile multipartFile = new MockMultipartFile("firstDocument", inputStream);
    ContactInformation contactInformation = new ContactInformation();
    contactInformation.setEmail("email");
    contactInformation.setCountryOfOrigin("country");
    contactInformation.setLocalGovernment("local");
    var candidateToBeRegistered = CandidateRequest.builder()
            .biography("biography")
            .dateOfBirth(LocalDate.of(2002,06,21))
            .financialDisclosureForm(multipartFile)
            .nominationForm(multipartFile)
            .contactInformation(contactInformation)
            .username("username")
            .password("1234")
            .positionContested("president")
            .partyAffiliation("affiliation")
            .build();
    var response = adminService.registerCandidate(candidateToBeRegistered);
    assertThat(response).isNotNull();
}

}
