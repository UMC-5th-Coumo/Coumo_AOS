package com.umc.coumo.domain.type

enum class CouponAlignType(val align: String,val api: String) {
    MOST("가장 많이 적립한 순서","most"),
    RECENT("가장 최근 적립한 순서","latest")
}