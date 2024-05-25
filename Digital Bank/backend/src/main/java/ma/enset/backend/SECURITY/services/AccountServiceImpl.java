package ma.enset.backend.SECURITY.services;

import jakarta.transaction.Transactional;
import ma.enset.backend.SECURITY.entities.Role;
import ma.enset.backend.SECURITY.entities.UserEntity;
import ma.enset.backend.SECURITY.repositories.roleRepo;
import ma.enset.backend.SECURITY.repositories.userRepo;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
    private userRepo appUserRepository;
    private roleRepo appRoleRepository;

    public AccountServiceImpl(userRepo appUserRepository, roleRepo appRoleRepository) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
    }

    public UserEntity newUser(UserEntity appUser){
        return appUserRepository.save(appUser);
    }
    public Role newRole(Role appRole){
        return appRoleRepository.save(appRole);
    }
    public void addRoleToUser(String userName,String roleName){
        UserEntity appUser=appUserRepository.findByUsername(userName);
        Role appRole=appRoleRepository.findByRoleName(roleName);
        appUser.getAppRoles().add(appRole);
    }
    public UserEntity findByUserName(String userName){
        return appUserRepository.findByUsername(userName);
    }
}
