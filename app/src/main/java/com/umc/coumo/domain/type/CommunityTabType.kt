package com.umc.coumo.domain.type

enum class CommunityTabType(val title: String?, val api: String?) {
    ALL("우리동네 소식들 모아보기", null ),
    NEW_PRODUCT("우리동네 신메뉴들을 찾아봤어요", "NEW_PRODUCT"),
    NO_SHOW("우리동네 노쇼빈자리 SALE을 찾아봤어요","NO_SHOW"),
    EVENT("우리동네 이벤트들을 찾아봤어요","EVENT")
}