package ru.job4j.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import ru.job4j.domain.Passport;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PassportStore {

    @Value("${api-url}")
    private String url;

    private final RestTemplate client;

    public PassportStore(RestTemplate client) {
        this.client = client;
    }

    public Passport update(int id, Passport passport) {
        return client.exchange(
                String.format("%s/update?id=%s", url, id),
                HttpMethod.PUT,
                new HttpEntity<>(passport),
                Passport.class
        ).getBody();
    }

    public Passport save(Passport passport) {
        System.out.println(url);
        return client.postForEntity(
                String.format("%s/save", url),
                passport,
                Passport.class
        ).getBody();
    }

    public boolean delete(int id) {
        return client.exchange(
                String.format("%s/delete?id=%s", url, id),
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Void.class
        ).getStatusCode() != HttpStatus.NOT_FOUND;
    }

    public List<Passport> findAll() {
        return getList(String.format(
                "%s/find", url
        ));
    }

    public List<Passport> findBySerial(int serial) {
        return getList(String.format(
                "%s/find?serial=%s", url, serial
        ));
    }

    public List<Passport> findUnavailable() {
        return getList(String.format(
                "%s/unavailable", url
        ));
    }

    public List<Passport> findForReplace() {
        return getList(String.format(
                "%s/find-replaceable", url
        ));
    }

    private List<Passport> getList(String url) {
        List<Passport> body = client.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Passport>>() {
                }
        ).getBody();
        return body == null ? Collections.emptyList() : body;
    }
}
