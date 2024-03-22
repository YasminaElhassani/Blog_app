package io.novelis.novelisblogapp.service.enums;

public enum Role{

    USER("USER"), AUTHER("AUTHER"), ADMIN("ADMIN");

    public final String role;

    private Role(String role) {
        this.role = role;
    }
}
