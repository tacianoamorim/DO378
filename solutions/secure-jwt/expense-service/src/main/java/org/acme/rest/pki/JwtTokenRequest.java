package org.acme.rest.pki;

import java.util.Set;
import javax.json.bind.annotation.JsonbCreator;


public class JwtTokenRequest {

    private String upn;
    private Set<String> groups;
    private String token; 


    public JwtTokenRequest(String upn, Set<String> groups) {
        this.upn = upn;
        this.groups = groups;
    }

    public JwtTokenRequest(String upn, Set<String> groups, String token) {
        this(upn, groups);
        this.token = token;
    }

    @JsonbCreator
    public static JwtTokenRequest of(String upn, Set<String> groups) {
        return new JwtTokenRequest(upn, groups);
    }

    public String getUpn() {
        return this.upn;
    }

    public void setUpn(String upn) {
        this.upn = upn;
    }

    public Set<String> getGroups() {
        return this.groups;
    }

    public void setGroups(Set<String> groups) {
        this.groups = groups;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
