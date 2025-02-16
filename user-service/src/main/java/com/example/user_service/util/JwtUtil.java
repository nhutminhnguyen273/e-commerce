package com.example.user_service.util;

import com.example.user_service.model.Role;
import com.example.user_service.model.User;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
public class JwtUtil {
    @NonFinal
    @Value("${jwt.signerKey}")
    private String signerKey;
    @NonFinal
    @Value("${jwt.validDuration}")
    private long validDuration;
    @NonFinal
    @Value("${jwt.refreshableDuration}")
    private long refreshableDuration;

    public String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(validDuration, ChronoUnit.HOURS).toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .claim("role", buildScope(user))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(signerKey.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException ex) {
            log.error("Cannot create token", ex);
            throw new RuntimeException(ex);
        }
    }

    private String buildScope(User user) {
        Role role = user.getRole();
        return (role != null) ? role.getName() : "Customer";
    }
}