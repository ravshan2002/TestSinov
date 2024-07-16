package com.company.service;
import com.company.dto.ProfileDTO;
import com.company.entity.EmailCodeEntity;
import com.company.entity.ProfileEntity;
import com.company.enums.Role;
import com.company.enums.Status;
import com.company.exp.BadRequestException;
import com.company.repository.EmailCodeRepository;
import com.company.repository.MyQueryRepositoryImpl;
import com.company.repository.ProfileRepository;
import com.company.util.JWTUtil;
import com.company.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private MyQueryRepositoryImpl myQueryRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private EmailCodeRepository emailCodeRepository;

    public String registration(ProfileDTO profileDTO){

        ProfileEntity entity = new ProfileEntity();
        entity.setEmail(profileDTO.getEmail());
        entity.setName(profileDTO.getName());
        entity.setRole(Role.ROLE_USER);
        entity.setPhone(profileDTO.getPhone());
        entity.setPassword(profileDTO.getPassword());
        entity.setSurname(profileDTO.getSurname());
        entity.setStatus(Status.BLOCK);

        profileRepository.save(entity);

        String smsCode = RandomUtil.getRandomSmsCode();

        SendEmailCodeService.sendMail(profileDTO.getEmail(), "Tasdiqlash kodi: "+smsCode);

        EmailCodeEntity emailCode = new EmailCodeEntity();
        emailCode.setCode(smsCode);
        emailCode.setUserId(entity.getId());
        emailCode.setDateTime(LocalDateTime.now());
        emailCode.setStatus(Status.ACTIVE);

        emailCodeRepository.save(emailCode);

        return "success";
    }

    public ProfileDTO authorizationVerification(Integer userId, String code) {

        Optional<EmailCodeEntity> emailCode = emailCodeRepository.findByUserIdAndStatus(userId, Status.ACTIVE);

        if (emailCode.isEmpty()){
            throw new BadRequestException("faol smsCode mavjud emas");
        }

        EmailCodeEntity emailCode1 = emailCode.get();

        if (LocalDateTime.now().isAfter(emailCode1.getDateTime().plusMinutes(2))){
            throw new BadRequestException("Vaqt tugadi");
        }

        if(!emailCode1.getCode().equals(code)){
            throw new BadRequestException("Code xato");
        }

        emailCodeRepository.updateEmailCodeStatus(userId, Status.BLOCK);
        Optional<ProfileEntity> profileEntity = profileRepository.updateProfileStatus(userId, Status.ACTIVE);

        ProfileEntity entity = profileEntity.get();

        String encode = JWTUtil.encode(userId, entity.getEmail(), entity.getRole());

        ProfileDTO dto = profileService.fromEntityToDTO(entity);
        dto.setJwt(encode);

        return dto;
    }

    public void sinov() {

//        profileRepository.sinov("select * from profile");
        List list = myQueryRepository.executeQuery("select * from profile");

    }

}
