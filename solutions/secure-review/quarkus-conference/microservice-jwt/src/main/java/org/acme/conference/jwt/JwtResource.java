package org.acme.conference.jwt;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import io.smallrye.jwt.build.Jwt;

@Path("/jwt")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JwtResource {

    @GET
    public SecurityContext debugJwt(@Context SecurityContext ctx) {
        return ctx;
    }

    @POST
    public JwtTokenRequest generateToken(JwtTokenRequest tokenRequest) {
        String token =
           Jwt.issuer("http://do378.example.com/issuer")
             .upn(tokenRequest.getUpn())
             .groups(tokenRequest.getGroups())
             .expiresAt(Instant.now().plusMillis(TimeUnit.HOURS.toMillis(1)))
           .sign();
        tokenRequest.setToken(token);
        return tokenRequest;
    }
}