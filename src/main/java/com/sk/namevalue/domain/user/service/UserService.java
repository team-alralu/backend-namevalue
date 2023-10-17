package com.sk.namevalue.domain.user.service;

import com.sk.namevalue.domain.name.service.NameValueService;
import com.sk.namevalue.domain.user.dao.UserRepository;
import com.sk.namevalue.domain.user.domain.UserEntity;
import com.sk.namevalue.domain.user.dto.RequiredInfoDto;
import com.sk.namevalue.global.exception.DataNotFoundException;
import com.sk.namevalue.global.exception.ErrorMessage;
import com.sk.namevalue.global.exception.NotProcessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * title        : UserService
 * author       : sim
 * date         : 2023-10-18
 * description  : UserService
 */

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final NameValueService nameValueService;


    /**
     * 필수 정보 등록
     * @param userId - 유저 ID
     * @param request - 요청 Dto
     */
    public void regRequiredInfo(Long userId, RequiredInfoDto.Request request){

        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                () -> new DataNotFoundException(ErrorMessage.INVALID_USER));

        if(userEntity.isRequiredInfoRegFlag()){
            throw new NotProcessException(ErrorMessage.ALREDAY_REGISTRATION_REQUIRED_INFO);
        }

        nameValueService.save(userId, request.getNameInfo());

        userEntity.updateMBTI(request.getMbti());
        userEntity.updateRequiredInfoRegFlag(true);

        userRepository.save(userEntity);
        log.info(userId+" 계정의 필수 입력 정보가 등록되었습니다.");
    }
}
