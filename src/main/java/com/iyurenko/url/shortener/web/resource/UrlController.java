package com.iyurenko.url.shortener.web.resource;

import com.iyurenko.url.shortener.service.UrlContractionService;
import com.iyurenko.url.shortener.web.dto.UrlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * UrlController.
 *
 * @author iyurenko
 * @since 13.12.17.
 */
@Controller
public class UrlController {

    @Autowired
    private UrlContractionService urlContractionService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/{contractedUrl}")
    public void redirect(@PathVariable String contractedUrl, HttpServletResponse resp) throws Exception {
        String url = urlContractionService.getUrlByContractedUrl(contractedUrl);

        if (StringUtils.isEmpty(url)) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            resp.sendRedirect(url);
        }
    }

    @PostMapping("/contractions")
    public @ResponseBody String contractUrlAndSave(@ModelAttribute UrlDto contraction) {
        return urlContractionService.contractUrlAndSave(contraction);
    }

}
