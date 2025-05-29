package com.example.kuitandroidapiexample.ui.register.model

enum class AddAnimalStateType(
    val addState: String
) {
    PENDING("등록중"),
    ADDSUCCESS("등록성공"),
    ADDFAILURE("등록실패")
}