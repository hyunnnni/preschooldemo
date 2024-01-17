package com.preschool.preschooldemo.common;



public class Const {

    public static final int TEACHER = 2; //관리자 선생님 PK
    public static final int BOSS = 3; //관리자 원장님 PK
    public static final int RESULT = 100; // INT 초기화 시 넣을 더미 값
    public static final int FAIL = -1; //실패 응답값-----------
    public static final int SUCCESS = 1; //성공 응답값--------
    public static final int ZERO = 0; // 영향 받은 행이 없을 때나 초기화 시 넣을 더미 값


    public static final int KID_PAR_MANAGE_ROWCOUNT = 12; // 관리자 입장 원생, 학부모 관리 페이지 조회 시 ROWCOUNT
    public static final int NO_PERMISSION = -2; // 관리자만 접근할 수 있는 페이지에 접근 시 에러 응답값
    public static final int NO_INFORMATION = -3; // 해당 정보로 조회 시 조회되는 정보 없음

    public static final int STATE_GRADUATE = -1; // 원아졸업상태
    public static final int STATE_DROP_OUT = -2; // 원아퇴소상태
    public static final int FAKE_IS_DEL = 1; //데이터 DELETE인 척 처리 시
    public static final int UPD_STATE_FAIL = -3; // 재원상태/반 수정 실패
    public static final int UPD_IS_DEL_FAIL = -4; // isdel처리 실패




}
