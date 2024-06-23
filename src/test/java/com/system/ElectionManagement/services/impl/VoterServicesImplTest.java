package com.system.ElectionManagement.services.impl;

import com.github.fge.jsonpatch.JsonPatch;
import com.system.ElectionManagement.dtos.requests.ElectionRequest;
import com.system.ElectionManagement.dtos.requests.UpdateRequest;
import com.system.ElectionManagement.dtos.requests.VoterRequest;
import com.system.ElectionManagement.dtos.responses.UpdateResponse;
import com.system.ElectionManagement.exceptions.ElectionManagementException;
import com.system.ElectionManagement.models.Address;
import com.system.ElectionManagement.models.ContactInformation;
import com.system.ElectionManagement.models.EligibilityStatus;
import com.system.ElectionManagement.models.Voter;
import com.system.ElectionManagement.repositories.VoterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Set;

import static com.system.ElectionManagement.models.ElectionCategory.LOCAL_GOVERNMENT;
import static com.system.ElectionManagement.models.EligibilityStatus.ELIGIBLE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class VoterServicesImplTest {

    @Autowired
    private VoterServicesImpl voterServices;
    @Autowired
    private VoterRepository voterRepository;

    @Test
    void registerVoter() {
        ContactInformation initialContactInformation = getContactInformation();
        VoterRequest voterRequest = VoterRequest.builder()
                .firstName("InitialFirstName")
                .lastName("InitialLastName")
                .username("initialuser")
                .password("initialpassword")
                .dateOfBirth(LocalDate.parse("1990-01-01"))
                .eligibilityStatus(ELIGIBLE)
                .contactInformation(initialContactInformation)
                .build();
        var voterResponse = voterServices.registerVoter(voterRequest);
        assertThat(voterResponse).isNotNull();
        assertThat(voterResponse.getFirstName()).isEqualTo("InitialFirstName");
        assertThat(voterResponse.getContactInformation().getPhoneNumber()).isEqualTo("1234567890");
    }


    @Test
    void updateVoterInfo() {
        Voter initialVoter = registerVoterCheck();
        UpdateRequest updateRequest = UpdateRequest.builder()
                .id(initialVoter.getId())
                .firstName("UpdatedFirstName")
                .lastName("UpdatedLastName")
                .username("Updateduser")
                .password("updatedpassword")
                .dateOfBirth(LocalDate.parse("1992-01-02"))
                .eligibilityStatus(EligibilityStatus.ELIGIBLE)
                .build();
        UpdateResponse updateResponse = voterServices.updateVoterInfo(updateRequest);
        assertNotNull(updateResponse);
        assertThat(updateResponse.getFirstName()).isEqualTo("UpdatedFirstName");
        assertThat(updateResponse.getLastName()).isEqualTo("UpdatedLastName");
    }
    @Test
    public void testToRegisterVoterBelowAgeThrowsException(){
        ContactInformation initialContactInformation = getContactInformation();
        VoterRequest voterRequest = VoterRequest.builder()
                .firstName("InitialFirstName")
                .lastName("InitialLastName")
                .username("initialuser1")
                .password("initialpassword")
                .contactInformation(initialContactInformation)
                .dateOfBirth(LocalDate.parse("2024-01-03"))
                .build();
        assertThatThrownBy(() -> voterServices.registerVoter(voterRequest))
                .isInstanceOf(ElectionManagementException.class)
                .hasMessageContaining("you are not eligible to vote");
    }

    private static ContactInformation getContactInformation() {
        Address address = new Address();
        address.setCity("lagos");
        address.setZipCode("11111");
        address.setPostalCode("22222");
        return ContactInformation.builder()
                .phoneNumber("1234567890")
                .email("test@example.com")
                .address(address)
                .stateOfOrigin("state")
                .localGovernment("local_government")
                .countryOfOrigin("country")
                .build();
    }
    private Voter registerVoterCheck() {
        Voter voter = Voter.builder()
                .firstName("InitialFirstName")
                .lastName("InitialLastName")
                .username("initialuser")
                .password("initialpassword")
                .dateOfBirth(LocalDate.parse("1990-01-01"))
                .eligibilityStatus(EligibilityStatus.ELIGIBLE)
                .build();
        return voterRepository.save(voter);
    }
    //pushed code by michael

}
