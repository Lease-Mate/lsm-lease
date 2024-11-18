package com.lsm.ws.lease.infrastructure.rest.user;

import com.lsm.ws.lease.domain.user.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserFacadeRepository implements UserRepository {

    private final UserClient userClient;

    public UserFacadeRepository(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public void verifyToken(String token) {
        userClient.verify(token);
    }
}
