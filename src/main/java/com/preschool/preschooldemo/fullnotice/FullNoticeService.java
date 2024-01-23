package com.preschool.preschooldemo.fullnotice;

import com.google.firestore.v1.StructuredAggregationQuery;
import com.preschool.preschooldemo.common.Const;
import com.preschool.preschooldemo.common.MyFileUtils;
import com.preschool.preschooldemo.common.ResVo;
import com.preschool.preschooldemo.fullnotice.model.*;
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

//-------------------------------- 유치원 소식 작성 --------------------------------

    public ResVo postFullNotice (InsFullNoticeDto dto){

        Integer fix = mapper.selNoticeFix(dto.getIfullNotice());

        if (fix >= 3 && dto.getFullNoticeFix() == 1){
            return new ResVo(Const.FIX_MAX);
        }

        int result = mapper.postFullNotice(dto);
        InsFullPicsDto pdto = new InsFullPicsDto();

        if(result == 0){
            return new ResVo(Const.FAIL);
        }
        if(dto.getFullPic()== null){
            return new ResVo(Const.SUCCESS);
        }
        pdto.setIfullNotice(dto.getIfullNotice());
        String target = "/fullnotice/" + dto.getIfullNotice();

        for(MultipartFile file : dto.getFullPic()){
            String saverFileNm = mfu.transferTo(file, target);
            pdto.getFullPic().add(saverFileNm);
        }

        int picResult = mapper.postFullNoticePics(pdto);

        if (picResult < 1){
            return new ResVo(Const.PICS_FAIL);
        }
        return new ResVo(dto.getIfullNotice());
    }

//-------------------------------- 유치원 소식 삭제 --------------------------------

    public ResVo delFullNotice(DelFullNoticeDto dto){

        if(dto.getIlevel() == 2) {
            Integer writer = mapper.selFullNoticeWriter(dto.getIfullNotice());

            if (writer == null || writer != dto.getIteacher()){
                return new ResVo(Const.BAD_PARAMETER);
            }
        }
        int result = mapper.delFullNoticePics(dto);

        if (result == 0){
            return new ResVo(Const.PICS_FAIL);
        }

        int result1 = mapper.delFullNotice(dto);

        if (result1 == 0){
            return new ResVo(Const.FAIL);
        }
        return new ResVo(Const.SUCCESS);
    }

//-------------------------------- 유치원 소식 수정 --------------------------------

    public ResVo putFullNotice(UpdFullNoticeDto dto){

        Integer fix = mapper.selNoticeFix(dto.getIfullNotice());

        if (fix >= 3 && dto.getFullNoticeFix() == 1){
            return new ResVo(Const.FIX_MAX);
        }

        int result = mapper.putFullNotice(dto);
        Integer selResult = mapper.selFullNoticePics(dto.getIfullNotice());
        InsFullPicsDto pdto = new InsFullPicsDto();

        if(result == 0){
            return new ResVo(Const.FAIL);
        }
        if(dto.getFullPic() == null){
            return new ResVo(Const.SUCCESS);
        }
        if (selResult > 0){
            int delResult = mapper.delUpdFullNoticePics(dto.getIfullNotice());
            if(delResult == 0){
                return new ResVo(Const.PICS_UP_FAIL);
            }
        }
        pdto.setIfullNotice(dto.getIfullNotice());
        String target = "/fullnotice/" + dto.getIfullNotice();

        for(MultipartFile file : dto.getFullPic()){
            String saverFileNm = mfu.transferTo(file, target);
            pdto.getFullPic().add(saverFileNm);
        }

        int picResult = mapper.postFullNoticePics(pdto);

        if (picResult < 1){
            return new ResVo(Const.PICS_FAIL);
        }
        return new ResVo(dto.getIfullNotice());

    }
//-------------------------------- 유치원 소식 수정 시 불러오기 --------------------------------

    public SelFullNoticeUpdVo getFullNoticeUpd(SelFullNoticeUpdDto dto){
        SelFullNoticeUpdVo vo = mapper.selFullNoticeUpd(dto.getIfullNotice());

        if(vo == null){
            SelFullNoticeUpdVo result = new SelFullNoticeUpdVo();
            result.setResult(Const.NO_INFORMATION);
            return result;
        }

        List<String> picList = mapper.selFullNoticeUpdPics(dto.getIfullNotice());

        if (picList.size() == 0){
            vo.setResult(Const.ONLY_CONTENTS);
            return vo;
        }
        vo.setFullPic(picList);

        return vo;
    }
}
