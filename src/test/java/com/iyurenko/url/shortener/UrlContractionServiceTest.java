package com.iyurenko.url.shortener;

import com.iyurenko.url.shortener.service.UrlContractionServiceImpl;
import com.iyurenko.url.shortener.dao.UrlContractionDao;
import com.iyurenko.url.shortener.web.dto.UrlDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.StringUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * UrlContractionServiceTest.
 *
 * @author iyurenko
 * @since 13.12.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UrlContractionServiceTest {

    @InjectMocks
    private UrlContractionServiceImpl urlContractionService;

    @Mock
    private UrlContractionDao urlContractionDao;

    @Before
    public void setUp() throws Exception {
        ReflectionTestUtils.setField(urlContractionService, "serverPort", 9000);
    }

    @Test
    public void testContractAndSaveUrlWithExistingContraction() {

        //given
        String sourceUrl = "https://twitter.com/";
        String contractedUrl = "151315675074349622930";
        UrlDto urlDto = new UrlDto(sourceUrl);

        Mockito.when(urlContractionDao.getContractionByUrl(sourceUrl)).thenReturn(contractedUrl);

        //when
        String contractedUrlWithHost = urlContractionService.contractUrlAndSave(urlDto);

        //then
        assertEquals("http://localhost:9000/" + contractedUrl, contractedUrlWithHost);
    }

    @Test
    public void testContractAndSaveUrlWithoutExistingContraction() {

        //given
        String sourceUrl = "https://twitter.com/";
        UrlDto urlDto = new UrlDto(sourceUrl);

        Mockito.when(urlContractionDao.getContractionByUrl(sourceUrl)).thenReturn(null);

        //when
        String contractedUrlWithHost = urlContractionService.contractUrlAndSave(urlDto);

        //then
        ArgumentCaptor<String> sourceUrlCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> randomContractedUrlCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(urlContractionDao).save(sourceUrlCaptor.capture(), randomContractedUrlCaptor.capture());

        assertFalse(StringUtils.isEmpty(contractedUrlWithHost));
        assertEquals("http://localhost:9000/" + randomContractedUrlCaptor.getValue(), contractedUrlWithHost);
    }

    @Test
    public void testContractAndSaveUrlWithNullUrlDto() {

        //given
        UrlDto urlDto = null;

        //when
        String contractedUrlWithHost = urlContractionService.contractUrlAndSave(urlDto);

        //then
        assertEquals(null, contractedUrlWithHost);
    }

    @Test
    public void testContractAndSaveUrlWithEmptyUrlDto() {
        //given
        UrlDto urlDto = new UrlDto();

        //when
        String contractedUrlWithHost = urlContractionService.contractUrlAndSave(urlDto);

        //then
        assertEquals(null, contractedUrlWithHost);
    }
}
