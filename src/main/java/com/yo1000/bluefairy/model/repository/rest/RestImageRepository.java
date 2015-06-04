package com.yo1000.bluefairy.model.repository.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yo1000.bluefairy.model.entity.docker.Image;
import com.yo1000.bluefairy.model.entity.docker.ImageCreated;
import com.yo1000.bluefairy.model.entity.docker.ImageInspect;
import com.yo1000.bluefairy.model.entity.docker.ImageSearch;
import com.yo1000.bluefairy.model.repository.ImageRepository;
import org.apache.http.conn.EofSensorInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.util.UriTemplate;
import org.springframework.web.util.UriUtils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by yoichi.kikuchi on 15/03/12.
 */
@Repository
public class RestImageRepository extends AbstractRestDockerRepository
        implements ImageRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestImageRepository.class);

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
        String url = "images/create?fromImage={image}";
        URI uri = null;

        try {
            URI expanded = new UriTemplate(url).expand(URLEncoder.encode(image, "UTF-8"));
            url = URLDecoder.decode(expanded.toString(), "UTF-8");
            uri = new URI(this.makeDockerRemoteApiEndpoint(url));
        }
        catch (UnsupportedEncodingException e) {
            LOGGER.warn("Failed to encode URL.", e);
        }
        catch (URISyntaxException e) {
            LOGGER.warn("Invalid URL.", e);
        }

        return this.getRestTemplate().execute(
                uri,
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
                }
        );
    }
}
