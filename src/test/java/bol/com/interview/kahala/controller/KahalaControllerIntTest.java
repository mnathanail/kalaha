package bol.com.interview.kahala.controller;

import bol.com.interview.kahala.dao.BoardDao;
import bol.com.interview.kahala.service.IBoardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(KahalaController.class)
class KahalaControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IBoardService service;

    @Autowired
    private KahalaController kahala;

    @Mock
    private BoardDao boardDao;

    @Test
    public void testMockMvc() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(kahala).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/start-game"))
                .andExpect(status().isOk());

    }

    @Test
    void initBoard() throws Exception {
        Mockito.when(service.initializeBoard())
                .thenReturn(boardDao.initializeBoard());

        RequestBuilder request = MockMvcRequestBuilders.get("/start-game");
        MvcResult result = mockMvc.perform(request).andReturn();

        System.out.println(result.getResponse().getContentAsString());
        String expected =
                "{\"southKahala\":{\"score\":0}," +
                "\"northKahala\":{\"score\":0}," +
                "\"northPits\":[" +
                    "{\"pos\":0,\"value\":6}," +
                    "{\"pos\":1,\"value\":6}," +
                    "{\"pos\":2,\"value\":6}," +
                    "{\"pos\":3,\"value\":6}," +
                    "{\"pos\":4,\"value\":6}," +
                    "{\"pos\":5,\"value\":6}]," +
                "\"southPits\":[" +
                    "{\"pos\":0,\"value\":6}," +
                    "{\"pos\":1,\"value\":6}," +
                    "{\"pos\":2,\"value\":6}," +
                    "{\"pos\":3,\"value\":6}," +
                    "{\"pos\":4,\"value\":6}," +
                    "{\"pos\":5,\"value\":6}]," +
                "\"winner\":false}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void playFirst() {
    }

    @Test
    void playNext() {
    }

    @Test
    void sow() {
    }

    @Test
    void winner() {
    }
}