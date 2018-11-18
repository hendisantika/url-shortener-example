package com.hendisantika.urlshortenerexample;

import com.google.common.hash.Hashing;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

/**
 * Created by IntelliJ IDEA.
 * Project : url-shortener-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 19/11/18
 * Time: 05.39
 * To change this template use File | Settings | File Templates.
 */
@RequestMapping("/rest/url")
@RestController
public class UrlShortenerResource {

    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/{id}")
    public String getUrl(@PathVariable String id) {

        String url = redisTemplate.opsForValue().get(id);
        System.out.println("URL Retrieved: " + url);

        if (url == null) {
            throw new RuntimeException("There is no shorter URL for : " + id);
        }
        return url;
    }

    @PostMapping
    public String create(@RequestBody String url) {

        UrlValidator urlValidator = new UrlValidator(
                new String[]{"http", "https"}
        );

        if (urlValidator.isValid(url)) {
            String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
            System.out.println("URL Id generated: " + id);
            redisTemplate.opsForValue().set(id, url);
            return id;
        }

        throw new RuntimeException("URL Invalid: " + url);
    }
}