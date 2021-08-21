package com.acme.homemade.demo;

import com.acme.homemade.demo.domain.model.Publication;
import com.acme.homemade.demo.domain.repository.PublicationRepository;
import com.acme.homemade.demo.domain.repository.UserNoChefRepository;
import com.acme.homemade.demo.domain.service.PublicationService;
import com.acme.homemade.demo.execption.ResourceNotFoundException;
import com.acme.homemade.demo.service.PublicationServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class PublicationServiceImpIntegrationTest {
    @MockBean
    private PublicationRepository publicationRepository;

    @MockBean
    private UserNoChefRepository userNoChefRepository;

    @Autowired
    private PublicationService publicationService;



    @TestConfiguration
    static class  PublicationServiceImpTestConfiguration {
        @Bean
        public PublicationService publicationService(){
            return new PublicationServiceImpl();
        }
    }


    @Test
    @DisplayName("When Get Publication By Id With Valid Id Then Returns Publication")
    public void whenGetPublicationByIdWithValidIdThenReturnsPublication(){
        //Arrange
        Publication publication = new Publication();
        publication.setId(1L);
        publication.setText("Me encanta la paguina homemade");
        when(publicationRepository.findById(1L)).thenReturn(Optional.of(publication));

        //Act
        Publication foundPublication = publicationService.getPublicationById(1L);

        //Assert
        assertThat(foundPublication.getId()).isEqualTo(1L);
    }


    @Test
    @DisplayName("When GetPostById With Invalid Id Then Returns ResourceNotFoundException")
    public void whenGetPostByIdWithInvalidIdThenReturnsResourceNotFoundException(){
        //Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(publicationRepository.findById(id)).thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Publication", "Id", id);

        //Act
        Throwable exception = catchThrowable(()->{
            Publication publication = publicationService.getPublicationById(id);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundException.class).hasMessage(expectedMessage);
    }

}
