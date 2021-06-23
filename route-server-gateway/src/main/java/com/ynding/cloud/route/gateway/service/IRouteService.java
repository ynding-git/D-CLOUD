package com.ynding.cloud.route.gateway.service;

import org.springframework.cloud.gateway.route.RouteDefinition;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author ynding
 */
public interface IRouteService {

    /**
     * @return
     */
    Flux<RouteDefinition> getRouteDefinitions();

    /**
     * @param routeDefinitionMono
     * @return
     */
    Mono<Void> save(Mono<RouteDefinition> routeDefinitionMono);

    /**
     * @param routeId
     * @return
     */
    Mono<Void> delete(Mono<String> routeId);
}
