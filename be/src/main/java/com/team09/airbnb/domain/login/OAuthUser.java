package com.team09.airbnb.domain.login;

public class OAuthUser {
    private String login;
    private String name;

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }
}
