package com.mohsenfn.apigatewayservice.Filter;

import com.mohsenfn.apigatewayservice.Util.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AutenticationFilter extends AbstractGatewayFilterFactory<AutenticationFilter.Config> {
    @Autowired
    private RouteValidator validator;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private JwtService jwt;

    public AutenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                //headers contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }
                String authheaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authheaders != null && authheaders.startsWith("Bearer ")) {
                    authheaders = authheaders.substring(7);
                }
                try {
                    //REST CALL TO AUTH SERVER
                    //restTemplate.getForObject("http://localhost:8087//auth/validate?token="+authheaders, String.class);
                    jwt.validateToken(authheaders);
                } catch (Exception e) {
                    throw new RuntimeException("unautorized acces");
                }
            }

            return chain.filter(exchange);
        }));
    }

    public static class Config {

    }
}