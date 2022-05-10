package com.codingwithmitch.daggerhiltplayground.use_case

data class UseCase(
    val getFromApi: GetFromApi,
    val getFromFirebase: GetFromFirebase,
    val postToFirebase: PostToFirebase
)
