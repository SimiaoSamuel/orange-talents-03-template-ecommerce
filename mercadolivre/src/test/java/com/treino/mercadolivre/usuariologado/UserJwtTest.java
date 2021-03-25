package com.treino.mercadolivre.usuariologado;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserJwtTest {
    @Autowired
    MockMvc mvc;

    @Test
    public void seUsuarioNaoTiverCadastradoRetornaBadRequest() throws Exception {
        String username = "xpto@gmail.com";
        String password = "senhaAleatoria";

        String body = "{\"login\":\"" + username + "\", \"senha\":\"" + password  + "\" }";

        mvc.perform(MockMvcRequestBuilders.post("/auth").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    public void seUsuarioTiverCadastradoRetornaToken() throws Exception {
        String username = "xpto2@gmail.com";
        String password = "senhaAleatoria2";

        String body = "{\"login\":\"" + username + "\", \"senha\":\"" + password  + "\" }";

        mvc.perform(MockMvcRequestBuilders.post("/usuarios").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk()).andReturn();

        mvc.perform(MockMvcRequestBuilders.post("/auth").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk()).andReturn();
    }
}
