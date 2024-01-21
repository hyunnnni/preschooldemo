package com.preschool.preschooldemo.noticeboard;

import com.preschool.preschooldemo.common.Const;
import com.preschool.preschooldemo.noticeboard.model.SelAllNoticeDto;
import com.preschool.preschooldemo.noticeboard.model.SelAllNoticeVo;
import com.preschool.preschooldemo.noticeboard.model.SelDetailNoticeDto;
import com.preschool.preschooldemo.noticeboard.model.SelDetailNoticeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
public class NoticeBoardService {
    private final NoticeBoardMapper mapper;

    //-------------------------------- 알림장 접근 유저에 따라 다르게 전체 조회 --------------------------------
    public List<SelAllNoticeVo> getKidManagement(SelAllNoticeDto dto){

        List<SelAllNoticeVo> voList = new ArrayList<>();
        SelAllNoticeVo vo = new SelAllNoticeVo();

        if(dto.getIlevel() == 1){
            voList = mapper.selAllNoticeBoardPar(dto);
        }
        if(dto.getIlevel() == 2 || dto.getIlevel() == 3){
            voList = mapper.selAllNoticeBoardTea(dto);
        }
        if(voList.size() == 0){
            vo.setResult(Const.NO_INFORMATION);
            voList.add(vo);
            return voList;
        }

        for(SelAllNoticeVo picCheck : voList){
            Integer result = mapper.selNoticeBoardPicCheck(picCheck.getInotice());
            if(result != null){
                picCheck.setPicCheck(Const.SUCCESS);
            }
        }

        return voList;
    }

//-------------------------------- 알림장 상세 조회 --------------------------------
    public SelDetailNoticeVo getDetailNotice(SelDetailNoticeDto dto){
return null;
    }
}
