package com.vaccinelife.vaccinelifeapi.service;

import com.vaccinelife.vaccinelifeapi.dto.MedicalLikeRequestDto;
import com.vaccinelife.vaccinelifeapi.dto.ResponseDto;
import com.vaccinelife.vaccinelifeapi.model.*;
import com.vaccinelife.vaccinelifeapi.repository.MedicalLikeRepository;
import com.vaccinelife.vaccinelifeapi.repository.MedicalRepository;
import com.vaccinelife.vaccinelifeapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicalLikeService {
    private final UserRepository userRepository;
    private final MedicalRepository medicalRepository;
    private final MedicalLikeRepository medicalLikeRepository;

    @Transactional
    public ResponseDto Like(MedicalLikeRequestDto requestDto) {
        Medical medical = medicalRepository.findById(requestDto.getMedicalId()).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다.")
        );

        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다.")
        );

        boolean isExist = medicalLikeRepository.existsByMedicalAndUser(medical, user);

        if (isExist) {
            medicalLikeRepository.deleteByMedicalAndUser(medical, user);
            medical.updateMedicalLikeNum(-1);
            return new ResponseDto(false, "Basic 게시글 좋아요 취소", 200);
        } else {
            MedicalLike medicalLike = new MedicalLike(medical, user);
            medicalLikeRepository.save(medicalLike);
            medical.updateMedicalLikeNum(+1);
            return new ResponseDto(true, "Basic 게시글 좋아요 추가", 200);
        }
    }

    public List<MedicalLikeRequestDto> getLike(Long id) {
        List<MedicalLike> medicalLike = medicalLikeRepository.findAllByUserId(id);
        if(id==null){
            new NullPointerException("아이디가 존재하지 않습니다.");
        }
        return MedicalLikeRequestDto.list(medicalLike);
    }


}
