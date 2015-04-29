package com.yo1000.bluefairy.model.repository.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yo1000.bluefairy.model.entity.docker.Image;
import com.yo1000.bluefairy.model.entity.docker.ImageCreated;
import com.yo1000.bluefairy.model.entity.docker.ImageInspect;
import com.yo1000.bluefairy.model.entity.docker.ImageSearch;
import com.yo1000.bluefairy.model.repository.ImageRepository;
import org.apache.http.conn.EofSensorInputStream;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by yoichi.kikuchi on 15/03/12.
 */
@Repository
public class RestImageRepository extends AbstractRestDockerRepository
        implements ImageRepository {
    @Override
    public Image[] getJson() {
        return this.getJson(false);
    }

    @Override
    public Image[] getJson(boolean all) {
        return this.getRestTemplate().getForObject(
                this.makeDockerRemoteApiEndpoint("images/json?all={all}"),
                Image[].class,
                all);
    }

    @Override
    public ImageInspect getInspect(String id) {
        return this.getRestTemplate().getForObject(
                this.makeDockerRemoteApiEndpoint("images/{id}/json"),
                ImageInspect.class,
                id
        );
    }

    @Override
    public ImageSearch[] getSearch(String keyword) {
        return this.getRestTemplate().getForObject(
                this.makeDockerRemoteApiEndpoint("images/search?term={keyword}"),
                ImageSearch[].class,
                keyword
        );
    }

    @Override
    public ImageCreated postCreate(String image) {
        return this.getRestTemplate().execute(
                this.makeDockerRemoteApiEndpoint("images/create?fromImage={image}"),
                HttpMethod.POST,
                new RequestCallback() {
                    @Override
                    public void doWithRequest(ClientHttpRequest clientHttpRequest) throws IOException {
                        //
                    }
                },
                new ResponseExtractor<ImageCreated>() {
                    @Override
                    public ImageCreated extractData(ClientHttpResponse clientHttpResponse) throws IOException {
                        InputStream input = clientHttpResponse.getBody();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                        ImageCreated imageCreated = new ObjectMapper().readValue(
                                reader.readLine(), ImageCreated.class);

                        if (input instanceof EofSensorInputStream) {
                            EofSensorInputStream eofSensorInput = (EofSensorInputStream) input;
                            eofSensorInput.abortConnection();
                        }

                        return imageCreated;
                    }
                },
                image
        );
    }
}
