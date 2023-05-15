package com.genius.tms_c61_genius.mapper;

import com.genius.tms_c61_genius.model.request.UserReqDto;
import com.genius.tms_c61_genius.model.response.UserResDto;
import com.genius.tms_c61_genius.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.genius.tms_c61_genius.model.domain.User;

@Component
public class UserDtoMapper {

    private final RoleRepository roleRepository;
    @Autowired
    public UserDtoMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public User userReqToUser(UserReqDto reqUser) {
        return User.builder().
                login(reqUser.getLogin()).
                nickName(reqUser.getNickname()).
                password(reqUser.getPassword()).
                role(roleRepository.findRoleByRoleName(reqUser.getRole()).get()).
                build();

    }

    public UserResDto userToUserRes(User user){
        return UserResDto.builder().
                login(user.getLogin()).
                nickname(user.getNickName()).
                role(user.getRole().getRoleName()).
                build();
    }
}
