package com.iyurenko.url.shortener.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * RedisUrlContractionDao.
 *
 * @author iyurenko
 * @since 13.12.17.
 */
@Repository
public class RedisUrlContractionDao implements UrlContractionDao {

    private String sourceUrlPrefix = "sourceUrl:";
    private String contractedUrlPrefix = "contractedUrl:";

    @Autowired
    private RedisTemplate<String, String> template;

    @Override
    public String getUrlByContraction(String contractedUrl) {
        return template.opsForValue().get(contractedUrlPrefix + contractedUrl);
    }

    @Override
    public String getContractionByUrl(String url) {
        return template.opsForValue().get(sourceUrlPrefix + url);
    }

    @Override
    public void save(String url, String contractedUrl) {
        template.opsForValue().setIfAbsent(sourceUrlPrefix + url, contractedUrl);
        template.opsForValue().setIfAbsent(contractedUrlPrefix + contractedUrl, url);
    }
}
