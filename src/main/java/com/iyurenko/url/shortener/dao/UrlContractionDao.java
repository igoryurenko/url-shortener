package com.iyurenko.url.shortener.dao;

/**
 * UrlContractionDao.
 *
 * @author iyurenko
 * @since 13.12.17.
 */
public interface UrlContractionDao {

    /**
     * Gets a source url by a contracted url
     *
     * @param contractedUrl contracted url
     * @return source url
     */
    String getUrlByContraction(String contractedUrl);

    /**
     * Gets a contracted url by a source url
     *
     * @param url - source url
     * @return contracted url
     */
    String getContractionByUrl(String url);

    /**
     * Saves url and its contracted url
     *
     * @param url source url
     * @param contractedUrl contracted url
     */
    void save(String url, String contractedUrl);

}
