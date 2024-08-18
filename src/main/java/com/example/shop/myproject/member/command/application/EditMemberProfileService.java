package com.example.shop.myproject.member.command.application;

import com.example.shop.myproject.common.domain.Gender;
import com.example.shop.myproject.member.command.application.dto.EditProfileRequest;
import com.example.shop.myproject.member.domain.Member;
import com.example.shop.myproject.member.domain.MemberRepository;
import com.example.shop.myproject.member.domain.Profile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class EditMemberProfileService {

    private final MemberRepository memberRepository;
    private final StoreProfileImage storeProfileImage;

    @Transactional
    public void editProfile(EditProfileRequest form) throws IOException {
        Member member = memberRepository.findById(form.getId()).orElseThrow(IllegalArgumentException::new);
        String image = storeProfileImage.storeFile(form.getProfileImageFile());
        member.updateProfile(new Profile(form.getNickname(),
                        form.getIntroduce(),
                        Gender.valueOf(form.getGender()),
                        image));
    }
}
