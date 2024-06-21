package com.system.ElectionManagement.services;
import com.system.ElectionManagement.dtos.requests.NominationRequest;
import com.system.ElectionManagement.dtos.responses.NominationResponse;
import com.system.ElectionManagement.models.ElectionCategory;
import com.system.ElectionManagement.models.NominationStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

import static com.system.ElectionManagement.models.ElectionCategory.NATIONAL;
import static com.system.ElectionManagement.models.NominationStatus.REJECTED;
import static com.system.ElectionManagement.models.NominationStatus.SUBMITTED;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = {"/db/data.sql"})
public class NominationFromTest {

    @Autowired
    NominationFormService service;

    @Test
    public void testCreateAndSubmitNominationForm() {
        NominationRequest nominationRequest = new NominationRequest();
        nominationRequest.setPositionContested("president");
        nominationRequest.setDateApproved(null);
        nominationRequest.setStatus(SUBMITTED);
        nominationRequest.setCategory(NATIONAL);
        nominationRequest.setDateSubmitted(LocalDateTime.now());
        NominationResponse nominationResponse = service.submit(nominationRequest);
        assertNotNull(nominationResponse);
        assertEquals(nominationRequest.getPositionContested(), nominationResponse.getPositionContested());
        assertEquals(nominationRequest.getDateSubmitted(), nominationResponse.getDateSubmitted());
        assertEquals(nominationRequest.getStatus(), nominationResponse.getNominationStatus());
        assertEquals(nominationRequest.getCategory(), nominationResponse.getElectionCategory());
    }


    @Test
    public void testCreateAndApproveNomination() {
        NominationRequest nominationRequest = new NominationRequest();
        nominationRequest.setPositionContested("governor");
        nominationRequest.setDateApproved(null);
        nominationRequest.setStatus(NominationStatus.SUBMITTED);
        nominationRequest.setCategory(ElectionCategory.LOCAL_GOVERNMENT);
        nominationRequest.setDateSubmitted(LocalDateTime.now());
        NominationResponse nominationResponse = service.submit(nominationRequest);
        assertNotNull(nominationResponse);
        assertEquals(nominationRequest.getPositionContested(), nominationResponse.getPositionContested());
        assertEquals(nominationRequest.getDateSubmitted(), nominationResponse.getDateSubmitted());
        assertEquals(nominationRequest.getStatus(), nominationResponse.getNominationStatus());
        assertEquals(nominationRequest.getCategory(), nominationResponse.getElectionCategory());
        NominationResponse approvedNomination = service.approveNomination(nominationResponse.getId());
        assertNotNull(approvedNomination);
        assertEquals(NominationStatus.APPROVED, approvedNomination.getNominationStatus());
        assertNotNull(approvedNomination.getDateApproved());
    }


    @Test
    public void testFindNominationFormById(){
        NominationResponse foundNominationForm = service.findById(200);
        assertNotNull(foundNominationForm);
        assertEquals(200, foundNominationForm.getId());
        assertEquals(SUBMITTED, foundNominationForm.getNominationStatus());
        assertEquals(LocalDateTime.parse("2024-06-04T15:03:03.792009700").withNano(0), foundNominationForm.getDateSubmitted().withNano(0));
        assertEquals(LocalDateTime.parse("2024-09-04T15:04:03.792009700").withNano(0), foundNominationForm.getDateApproved().withNano(0));
        assertEquals(NATIONAL, foundNominationForm.getElectionCategory());
    }

    @Test
    public void testFindAllNominationForms(){
        List<NominationResponse> allNominationForms = service.findAllNominationForms();
        assertNotNull(allNominationForms);
        assertEquals(4, allNominationForms.size());
    }


   @Test
   public void testCreateAndRejectNomination() {
       NominationRequest nominationRequest = new NominationRequest();
       nominationRequest.setPositionContested("senator");
       nominationRequest.setDateApproved(null);
       nominationRequest.setStatus(SUBMITTED);
       nominationRequest.setCategory(ElectionCategory.STATE);
       nominationRequest.setDateSubmitted(LocalDateTime.now());
       NominationResponse nominationResponse = service.submit(nominationRequest);
       assertNotNull(nominationResponse);
       assertEquals(SUBMITTED, nominationResponse.getNominationStatus());
       NominationResponse rejectedNomination = service.rejectNomination(nominationResponse.getId());
       assertNotNull(rejectedNomination);
       assertEquals(REJECTED, rejectedNomination.getNominationStatus());
       NominationResponse foundNominationForm = service.findById(rejectedNomination.getId());
       assertNotNull(foundNominationForm);
       assertEquals(REJECTED, foundNominationForm.getNominationStatus());

   }
    @Test
    public void testFindNonExistentNominationFormById() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.findById(999);
        });
        assertEquals("Nomination not found for id: 999", exception.getMessage());
    }

    @Test
    public void testCreateNominationInvalidData() {
        NominationRequest nominationRequest = new NominationRequest();
        nominationRequest.setPositionContested(null);
        nominationRequest.setDateSubmitted(null);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.submit(nominationRequest);
        });
        assertEquals("Invalid nomination data", exception.getMessage());

    }

    @Test
    public void testRejectAlreadyRejectedNomination() {
        NominationRequest nominationRequest = new NominationRequest();
        nominationRequest.setPositionContested("senator");
        nominationRequest.setDateApproved(null);
        nominationRequest.setStatus(SUBMITTED);
        nominationRequest.setCategory(ElectionCategory.STATE);
        nominationRequest.setDateSubmitted(LocalDateTime.now());
        NominationResponse nominationResponse = service.submit(nominationRequest);
        NominationResponse rejectedNomination = service.rejectNomination(nominationResponse.getId());
        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.rejectNomination(rejectedNomination.getId());
        });
        assertEquals("Nomination is already rejected", exception.getMessage());
    }


}







