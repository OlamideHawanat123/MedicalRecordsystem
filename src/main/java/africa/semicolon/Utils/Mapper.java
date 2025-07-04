package africa.semicolon.Utils;

import africa.semicolon.data.models.User;
import africa.semicolon.dtos.requests.RegisterUserRequest;

public class Mapper {
    public static User mapRequestToUser(RegisterUserRequest registerUserRequest) {
        User user = new User();
        user.setFirstName(registerUserRequest.getFirstName());
        user.setLastName(registerUserRequest.getLastName());
        user.setGender(registerUserRequest.getGender());
        user.setAddress(registerUserRequest.getAddress());
        user.setAddress(registerUserRequest.getAddress());
        user.setEmail(registerUserRequest.getEmail().toLowerCase());
        user.setPassword(registerUserRequest.getPassword());
        user.setGender(registerUserRequest.getGender());
        user.setAge(registerUserRequest.getAge());
        return user;
    }
}
