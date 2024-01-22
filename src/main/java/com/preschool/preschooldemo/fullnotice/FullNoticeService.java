package com.preschool.preschooldemo.fullnotice;

import com.google.firestore.v1.StructuredAggregationQuery;
import com.preschool.preschooldemo.common.Const;
import com.preschool.preschooldemo.common.MyFileUtils;
import com.preschool.preschooldemo.common.ResVo;
import com.preschool.preschooldemo.fullnotice.model.DelFullNoticeDto;
import com.preschool.preschooldemo.fullnotice.model.InsFullNoticeDto;
import com.preschool.preschooldemo.fullnotice.model.InsFullPicsDto;
import com.preschool.preschooldemo.fullnotice.model.UpdFullNoticeDto;
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

        if (fix >= 3 || fix == null){
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

        int result = Const.ZERO;

        if (dto.getIlevel() == 2 ){
            result = mapper.delFullNotice(dto);
        }
        if (dto.getIlevel() == 3 ){
            result = mapper.delFullNoticeBoss(dto.getIfullNotice());
        }
        if (result == 1){
            return new ResVo(Const.SUCCESS);
        }
        return new ResVo(Const.FAIL);
    }

//-------------------------------- 유치원 소식 상단 고정 기능 --------------------------------

    public ResVo putFullNotice(UpdFullNoticeDto dto){

        Integer fix = mapper.selNoticeFix(dto.getIfullNotice());

        if (fix >= 3 || fix == null){
            return new ResVo(Const.FIX_MAX);
        }

        int result = mapper.putFullNotice(dto);
        Integer selResult = mapper.selFullNoticePics(dto.getIfullNotice());

        if (selResult > 0){
            int delResult = mapper.delFullNoticePics(dto.getIfullNotice());
            if(delResult == 0){
                return new ResVo(Const.PICS_UP_FAIL);
            }
        }

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

}
