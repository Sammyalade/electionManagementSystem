package com.system.ElectionManagement.services.impl;

import com.system.ElectionManagement.dtos.requests.ElectionRequest;
import com.system.ElectionManagement.dtos.requests.VoterRequest;
import com.system.ElectionManagement.models.Address;
import com.system.ElectionManagement.models.ContactInformation;
import com.system.ElectionManagement.models.EligibilityStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Set;

import static com.system.ElectionManagement.models.ElectionCategory.LOCAL_GOVERNMENT;
import static com.system.ElectionManagement.models.EligibilityStatus.ELIGIBLE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class VoterServicesImplTest {

    @Autowired
    private VoterServicesImpl voterServices;

    @Test
    void registerVoter() {
        ContactInformation contactInformation = getContactInformation();
        VoterRequest request = VoterRequest.builder()
                .firstName("firstName")
                .lastName("lastName")
                .dateOfBirth(LocalDate.parse("1999-06-22"))
                .eligibilityStatus(ELIGIBLE)
                .contactInformation(contactInformation)
                .build();

        var voterResponse = voterServices.registerVoter(request);

        assertThat(voterResponse).isNotNull();
        assertThat(voterResponse.getFirstName()).isEqualTo("firstName");
        assertThat(voterResponse.getContactInformation().getPhoneNumber()).isEqualTo("1234567890");
    }

    private static ContactInformation getContactInformation() {
        Address address = new Address();
        address.setId(1L);
        address.setCity("lagos");
        address.setZipCode("11111");
        address.setPostalCode("22222");
        return ContactInformation.builder()
                .id(1L)
                .phoneNumber("1234567890")
                .email("test@example.com")
                .address(address)
                .stateOfOrigin("state")
                .localGovernment("local_government")
                .countryOfOrigin("country")
                .build();
    }

    @Test
    void updateVoterInfo() {
    }
}