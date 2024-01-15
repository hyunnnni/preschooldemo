package common;



public class Const {

    public static final int TEACHER = 2; //관리자 선생님 PK
    public static final int BOSS = 3; //관리자 원장님 PK
    public static final int FAIL = -1; //실패 응답값
    public static final int INTERNAL_SERVER_ERROR = 500; // 예외처리 오류

    public static final int KID_MANAGE_ROWCOUNT = 12; // 관리자 입장 원생관리 페이지 조회 시 ROWCOUNT
    public static final int NO_PERMISSION = -2; // 관리자만 접근할 수 있는 페이지에 접근 시 에러 응답값

}
