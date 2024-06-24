package com.system.ElectionManagement.controllers;

import com.system.ElectionManagement.dtos.requests.VoterRequest;
import com.system.ElectionManagement.models.ContactInformation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;
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
    void scheduleElection() {
    }

    @Test
    void reScheduleCandidate() {
    }
}