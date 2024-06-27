package com.system.ElectionManagement.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.ElectionManagement.dtos.requests.CandidateRequest;
import com.system.ElectionManagement.dtos.requests.VoterRequest;
import com.system.ElectionManagement.models.Address;
import com.system.ElectionManagement.models.ContactInformation;
import com.system.ElectionManagement.models.Vote;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Test
    public void testThatAddAdminEndpoint_addsAdmin(){
        String request = "{\n" +
                "  \"password\": \"1234567\",\n" +
                "  \"username\": \"ayo123\",\n" +
                "  \"firstName\": \"jargoh\",\n" +
                "  \"lastName\": \"999\"\n" +
                "}";

        try{
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/admin/add-admin")
                            .content(request)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print());

        } catch (Exception e) {
            assertThat(e).isNull();
        }
    }



    @Test
    void testThatRegisterVoterEndpoint_registersAVoter() {
        var request ="{\n" +
                "  \"contactInformation\": {\n" +
                "  \"address\": {\n" +
                "    \"postalCode\": \"1234\"\n" +
                "  },\n" +
                "    \"email\": \"@jinx999\"\n" +
                "  },\n" +
                "  \"username\": \"jinx999\",\n" +
                "  \"firstName\": \"jinx\",\n" +
                "  \"lastName\": \"999\",\n" +
                "  \"password\": \"1234\",\n" +
                "  \"eligibilityStatus\": \"ELIGIBLE\",\n" +
                "  \"dateOfBirth\": \"2000-03-03\"\n" +
                "}";

        try{
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/admin/register/voter")
                            .content(request)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful())

                    .andDo(print());

        } catch (Exception e) {
            assertThat(e).isNull();
        }
    }

    @Test
    void testRegisterCandidateEndpoint() throws JsonProcessingException {
        try {
            Path path = Paths.get("C:\\Users\\DELL\\Desktop\\New folder\\electionManagementSystem\\src\\main\\resources\\files\\Eric Schmidt, ex Google CEO says Bitcoin is a remarkable cryptographic achievement and the ability to create something that is not duplicable in the digital world has enormous value _ Double tap and hit follow if you agre.jpeg");
            byte[] fileContent = Files.readAllBytes(path);
            MockMultipartFile nominationForm = new MockMultipartFile("nominationForm", fileContent);
            MockMultipartFile financialDisclosureForm = new MockMultipartFile("financialDisclosureForm", fileContent);
            mockMvc.perform(multipart("/api/v1/admin/register/candidate")
                            .part(new MockPart("contactInformation.address.city", "Lagos".getBytes()))
                            .part(new MockPart("contactInformation.address.zipCode", "100254".getBytes()))
                            .part(new MockPart("contactInformation.address.streetName", "Herbert Macaulay".getBytes()))
                            .part(new MockPart("contactInformation.address.postalCode", "12345".getBytes()))
                            .part(new MockPart("contactInformation.phoneNumber", "08063725382".getBytes()))
                            .part(new MockPart("contactInformation.email", "email@email.com".getBytes()))
                            .part(new MockPart("contactInformation.stateOfOrigin", "Lagos".getBytes()))
                            .part(new MockPart("contactInformation.localGovernment", "Yaba".getBytes()))
                            .part(new MockPart("contactInformation.countryOfOrigin", "Nigeria".getBytes()))
                            .part(new MockPart("biography", "My Biography".getBytes()))
                            .part(new MockPart("partyAffiliation", "AAC".getBytes()))
                            .part(new MockPart("username", "username".getBytes()))
                            .part(new MockPart("password", "password".getBytes()))
                            .part(new MockPart("positionContested", "Governor".getBytes()))
                            .part(new MockPart("dateOfBirth", "1980-12-29".getBytes()))
                            .file(nominationForm.getName(), nominationForm.getBytes())
                            .file(financialDisclosureForm.getName(), financialDisclosureForm.getBytes())
                            .contentType(MediaType.MULTIPART_FORM_DATA))
                    .andExpect(status().isCreated())
                    .andDo(print());
        } catch (Exception e) {
            assertThat(e).isNull();
        }

    }

    private String getJsonRequest(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    private static CandidateRequest getCandidateRequest() {
        Address address = new Address();
        address.setPostalCode("1234");

        ContactInformation contactInfo = new ContactInformation();
        contactInfo.setEmail("@jinx999");
        contactInfo.setAddress(address);

        CandidateRequest candidateRequest = new CandidateRequest();
        candidateRequest.setUsername("jinx999");
        candidateRequest.setPassword("1234");
        candidateRequest.setContactInformation(contactInfo);
        candidateRequest.setDateOfBirth(LocalDate.of(2000, 3, 3));
        return candidateRequest;
    }

    @Test
    void scheduleElection() {
    }

    @Test
    void reScheduleCandidate() {
    }
}