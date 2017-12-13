package com.iyurenko.url.shortener.service;

import com.iyurenko.url.shortener.dao.UrlContractionDao;
import com.iyurenko.url.shortener.web.dto.UrlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * UrlContractionServiceImpl.
 *
 * @author iyurenko
 * @since 13.12.17.
 */
@Service
public class UrlContractionServiceImpl implements UrlContractionService {

    @Value("${server.port}")
    private int serverPort;

    @Autowired
    private UrlContractionDao urlContractionDao;

    @Override
    public String contractUrlAndSave(UrlDto urlDto) {

        String result = null;

        if (urlDto != null && urlDto.getUrl() != null) {

            String sourceUrl = urlDto.getUrl();
            String contractedUrl = urlContractionDao.getContractionByUrl(sourceUrl);

            if (StringUtils.isEmpty(contractedUrl)) {
                contractedUrl = randomizeUrl(sourceUrl);
                urlContractionDao.save(sourceUrl, contractedUrl);
            }

            result = "http://localhost:" + serverPort + "/" + contractedUrl;
        }

        return result;
    }

    @Override
    public String getUrlByContractedUrl(String contractedUrl) {
        return urlContractionDao.getUrlByContraction(contractedUrl);
    }

    /**
     *
     * Appends a currentTime string to a hashcode string in order to avoid collisions
     *
     * @param url some url
     * @return Random string of digits
     */
    private String randomizeUrl(String url) {
        String hashcode = String.valueOf(url.hashCode());
        String currentTime = String.valueOf(System.currentTimeMillis());
        return currentTime + hashcode;
    }
}
