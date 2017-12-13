package com.iyurenko.url.shortener.service;

import com.iyurenko.url.shortener.web.dto.UrlDto;

/**
 * UrlContractionService.
 *
 * @author iyurenko
 * @since 13.12.17.
 */
public interface UrlContractionService {

    /**
     * Performs url contraction and saves the result
     *
     * @param urlDto DTO with the url field
     * @return contracted url
     */
    String contractUrlAndSave(UrlDto urlDto);

    /**
     * Gets a source url by a contracted url
     *
     * @param contractedUrl a contracted url
     * @return source url
     */
    String getUrlByContractedUrl(String contractedUrl);

}
