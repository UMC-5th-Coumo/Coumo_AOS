package com.umc.coumo.domain.type

enum class CategoryType(val title: String, val api: String) {
    CAFE("카페/디저트", "cafe"),
    ENTERTAINMENT("오락/스포츠", "entertainment"),
    FOOD("요식업","food"),
    RETAIL("리테일","retail"),
    BEAUTY("뷰티/살롱","beauty"),
    CLASS("학원/클래스","class"),
    DEFAULT("","default")
}
