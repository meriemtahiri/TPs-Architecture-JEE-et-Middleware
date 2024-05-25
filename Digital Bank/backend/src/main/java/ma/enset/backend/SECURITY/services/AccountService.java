package ma.enset.backend.SECURITY.services;

import ma.enset.backend.SECURITY.entities.Role;
import ma.enset.backend.SECURITY.entities.UserEntity;

public interface AccountService {
    public UserEntity newUser(UserEntity appUser);
    public Role newRole(Role appRole);
    public void addRoleToUser(String userName,String roleName);
    public UserEntity findByUserName(String userName);
}
