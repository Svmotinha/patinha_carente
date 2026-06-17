package com.proj_social.carente.controller;

import com.proj_social.carente.model.InteresseAdocao;
import com.proj_social.carente.model.Pet;
import com.proj_social.carente.model.enums.StatusAdocao;
import com.proj_social.carente.model.enums.StatusSolicitacao;
import com.proj_social.carente.service.InteresseAdocaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InteresseAdocaoController.class)
class InteresseAdocaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InteresseAdocaoService interesseService;

    private InteresseAdocao interesse;
    private LocalDateTime dataVisita;

    @BeforeEach
    void setUp() {
        dataVisita = LocalDateTime.now().plusDays(5);
        
        Pet pet = Pet.builder()
                .id(1L)
                .nome("Fubá")
                .statusAdocao(StatusAdocao.Em_Processo)
                .build();

        interesse = InteresseAdocao.builder()
                .id(1L)
                .pet(pet)
                .statusSolicitacao(StatusSolicitacao.Confirmado)
                .dataVisita(dataVisita)
                .build();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void confirmarAgendamento_DeveRetornarOk() throws Exception {
        when(interesseService.confirmarAgendamento(eq(1L), any(LocalDateTime.class)))
                .thenReturn(interesse);

        mockMvc.perform(patch("/api/adocoes/1/confirmar")
                .param("dataVisita", dataVisita.toString())
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.statusSolicitacao").value("Confirmado"));
    }
}
