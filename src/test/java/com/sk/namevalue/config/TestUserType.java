package com.sk.namevalue.config;

/**
 * title        :
 * author       : sim
 * date         : 2023-10-13
 * description  :
 */

public enum TestUserType {

    VALID(1L, "valid@test.com");

    private final Long loginId;
    private final String email;

    TestUserType(Long loginId, String email){
        this.loginId = loginId;
        this.email = email;
    }

    public Long getLoginId() {
        return loginId;
    }

    public String getEmail(){
        return email;
    }
}
