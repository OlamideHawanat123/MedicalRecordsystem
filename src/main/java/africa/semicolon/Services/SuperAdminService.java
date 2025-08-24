package africa.semicolon.Services;

import africa.semicolon.dtos.requests.RemoveAdminRequest;
import africa.semicolon.dtos.responses.RemoveAdminResponse;

public interface SuperAdminService {
    RemoveAdminResponse removeAdmin(RemoveAdminRequest removeAdminRequest);
}
