package com.lsm.ws.lease.domain.user;

public interface UserRepository {

    void verifyToken(String token);
}
