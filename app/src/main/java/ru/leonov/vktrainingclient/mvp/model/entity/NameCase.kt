package ru.leonov.vktrainingclient.mvp.model.entity

//падеж для склонения имени и фамилии пользователя.
enum class NameCase {
    //именительный
    nom,

    // родительный
    gen,

    // дательный
    dat,

    // винительный
    acc,

    // творительный
    ins,

    // предложный
    abl
}