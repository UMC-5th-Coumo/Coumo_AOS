package com.umc.coumo.domain.type

enum class AccountAction(val title: String, val content: String) {
    LOGOUT("로그아웃하기","로그아웃 시, 재인증이 필요합니다"),
    WITHDRAW("탈퇴하기","탈퇴 시, 계정이 삭제되며 복구되지 않습니다");
}