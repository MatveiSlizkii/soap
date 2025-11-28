package com.example.soapTest.edpoint;



import com.example.soapTest.dto.User;
import com.example.soapTest.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserServiceEndpoint {

    private static final String NAMESPACE_URI = "http://example.com/soap/api";

    @Autowired
    private UserService userService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetUserRequest")
    @ResponsePayload
    public GetUserResponse getUser(@RequestPayload GetUserRequest request) {
        GetUserResponse response = new GetUserResponse();
        User user = userService.getUserById(request.getUserId());
        response.setUser(user);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateUserRequest")
    @ResponsePayload
    public CreateUserResponse createUser(@RequestPayload CreateUserRequest request) {
        CreateUserResponse response = new CreateUserResponse();

        try {
            User user = new User();
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());
            user.setPhone(request.getPhone());
            user.setBirthDate(request.getBirthDate());

            User createdUser = userService.createUser(user);

            response.setSuccess(true);
            response.setMessage("User created successfully");
            response.setUserId(createdUser.getId());
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error creating user: " + e.getMessage());
        }

        return response;
    }
}