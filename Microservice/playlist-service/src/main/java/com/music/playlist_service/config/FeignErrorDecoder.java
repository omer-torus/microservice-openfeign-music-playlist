package com.music.playlist_service.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

/**
 * 🔥 OpenFeign specific error decoder.
 * This class intercepts when Feign receives an error response from another service.
 * It allows us to translate situations like "service returned 404" or "service unavailable"
 * into meaningful exceptions.
 */
@Component
public class FeignErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()) {
            case 404:
                // User or song not found
                return new RuntimeException(
                        "Requested resource not found! (Feign 404) - Called method: " + methodKey);
            case 503:
                // Target service is down or unreachable
                return new RuntimeException(
                        "Target microservice is currently unreachable! (Feign 503) - Called method: " + methodKey);
            case 500:
                // Internal error in target service
                return new RuntimeException(
                        "Unexpected error occurred in target microservice! (Feign 500) - Called method: " + methodKey);
            default:
                // Diğer tüm hatalar için varsayılan Feign davranışı
                return defaultDecoder.decode(methodKey, response);
        }
    }
}
