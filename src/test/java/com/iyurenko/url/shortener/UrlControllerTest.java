package com.iyurenko.url.shortener;

import com.iyurenko.url.shortener.service.UrlContractionService;
import com.iyurenko.url.shortener.web.dto.UrlDto;
import com.iyurenko.url.shortener.web.resource.UrlController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * UrlControllerTest.
 *
 * @author iyurenko
 * @since 13.12.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UrlControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UrlController urlController;

    @Mock
    private UrlContractionService urlContractionService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(urlController)
                .build();
    }

    @Test
    public void testRedirect() throws Exception {
        when(urlContractionService.getUrlByContractedUrl(any(String.class))).thenReturn("https://twitter.com/");

        mockMvc.perform(get("/151315675074349622930"))
                .andExpect(status().isFound());
    }

    @Test
    public void testContractUrlAndSave() throws Exception {
        when(urlContractionService.contractUrlAndSave(any(UrlDto.class)))
                .thenReturn("http://localhost:9000/151315675074349622930");

        mockMvc.perform(post("/contractions"))
                .andExpect(status().isOk());
    }
}
