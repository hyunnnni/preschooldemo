package com.preschool.preschooldemo.fullnotice;

import com.google.firestore.v1.StructuredAggregationQuery;
import com.preschool.preschooldemo.common.Const;
import com.preschool.preschooldemo.common.MyFileUtils;
import com.preschool.preschooldemo.common.ResVo;
import com.preschool.preschooldemo.exception.AuthErrorCode;
import com.preschool.preschooldemo.exception.CommonErrorCode;
import com.preschool.preschooldemo.exception.RestApiException;
import com.preschool.preschooldemo.fullnotice.model.*;
import com.preschool.preschooldemo.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FullNoticeService {
    private final FullNoticeMapper mapper;
    private final MyFileUtils mfu;
    private final AuthenticationFacade authenticationFacade;

//-------------------------------- 유치원 소식 작성 --------------------------------

    public ResVo postFullNotice (InsFullNoticeDto dto){
        try {
            int level = authenticationFacade.getLevelPk();
            dto.setIlevel(level);

            if (!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)) {
                throw new RestApiException(AuthErrorCode.NO_PERMISSION);
            }

            Integer fix = mapper.selNoticeFix(dto.getIfullNotice());

            if (fix >= 3 && dto.getFullNoticeFix() == 1) {
                throw new RestApiException(AuthErrorCode.OVER_FIX_NOTICE);
            }

            int result = mapper.insFullNotice(dto);
            InsFullPicsDto pdto = new InsFullPicsDto();

            if (result == 0) {
                throw new RestApiException(AuthErrorCode.FAIL);
            }
            if (dto.getFullPic() == null) {
                return new ResVo(Const.SUCCESS);
            }
            pdto.setIfullNotice(dto.getIfullNotice());
            String target = "/fullnotice/" + dto.getIfullNotice();

            for (MultipartFile file : dto.getFullPic()) {
                String saverFileNm = mfu.transferTo(file, target);
                pdto.getFullPic().add(saverFileNm);
            }

            int picResult = mapper.insFullNoticePics(pdto);

            if (picResult < 1) {
                throw new RestApiException(AuthErrorCode.PICS_FAIL);
            }
            return new ResVo(dto.getIfullNotice());
        }catch (Exception e){
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

//-------------------------------- 유치원 소식 삭제 --------------------------------

    public ResVo delFullNotice(DelFullNoticeDto dto){

        try {
            int level = authenticationFacade.getLevelPk();
            dto.setIlevel(level);

            if (!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)) {
                throw new RestApiException(AuthErrorCode.NO_PERMISSION);
            }

            if (dto.getIlevel() == 2) {
                Integer writer = mapper.selFullNoticeWriter(dto.getIfullNotice());

                if (writer == null || writer != dto.getIteacher()) {
                    throw new RestApiException(CommonErrorCode.INVALID_PARAMETER);
                }
            }
            int result = mapper.delFullNoticePics(dto);

            if (result == 0) {
                throw new RestApiException(AuthErrorCode.PICS_FAIL);
            }

            int result1 = mapper.delFullNotice(dto);

            if (result1 == 0) {
                throw new RestApiException(AuthErrorCode.FAIL);
            }
            return new ResVo(Const.SUCCESS);

        }catch (Exception e){
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

//-------------------------------- 유치원 소식 수정 --------------------------------

    public ResVo putFullNotice(UpdFullNoticeDto dto){

        try {
            int level = authenticationFacade.getLevelPk();
            dto.setIlevel(level);

            if (!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)) {
                throw new RestApiException(AuthErrorCode.NO_PERMISSION);
            }

            Integer fix = mapper.selNoticeFix(dto.getIfullNotice());

            if (fix >= 3 && dto.getFullNoticeFix() == 1) {
                throw new RestApiException(AuthErrorCode.OVER_FIX_NOTICE);
            }

            int result = mapper.putFullNotice(dto);
            Integer selResult = mapper.selFullNoticePics(dto.getIfullNotice());
            InsFullPicsDto pdto = new InsFullPicsDto();

            if (result == 0) {
                throw new RestApiException(AuthErrorCode.FAIL);
            }
            if (dto.getFullPic() == null) {
                return new ResVo(Const.SUCCESS);
            }
            if (selResult > 0) {
                int delResult = mapper.delUpdFullNoticePics(dto.getIfullNotice());
                if (delResult == 0) {
                    throw new RestApiException(AuthErrorCode.PICS_FAIL);
                }
            }
            pdto.setIfullNotice(dto.getIfullNotice());
            String target = "/fullnotice/" + dto.getIfullNotice();

            for (MultipartFile file : dto.getFullPic()) {
                String saverFileNm = mfu.transferTo(file, target);
                pdto.getFullPic().add(saverFileNm);
            }

            int picResult = mapper.insFullNoticePics(pdto);

            if (picResult < 1) {
                throw new RestApiException(AuthErrorCode.PICS_FAIL);
            }
            return new ResVo(dto.getIfullNotice());

        }catch (Exception e){
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }

    }
//-------------------------------- 유치원 소식 수정 시 불러오기 --------------------------------

    public SelFullNoticeUpdVo getFullNoticeUpd(SelFullNoticeUpdDto dto){

        try {
            int level = authenticationFacade.getLevelPk();
            dto.setIlevel(level);

            if (!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)) {
                throw new RestApiException(AuthErrorCode.NO_PERMISSION);
            }

            SelFullNoticeUpdVo vo = mapper.selFullNoticeUpd(dto.getIfullNotice());

            if (vo == null) {
                throw new RestApiException(AuthErrorCode.NO_INFORMATION);
            }

            List<String> picList = mapper.selFullNoticeUpdPics(dto.getIfullNotice());

            vo.setFullPic(picList);

            return vo;

        }catch (Exception e){
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }

    }
}
