package it.macgood.mathanappkt.common

import it.macgood.mathanappkt.ui.guidebook.Guide

object Constants {

    const val DOWNLOAD_URL = "https://math-analysis-dev-server-production-63ae.up.railway.app/"

    var list: MutableList<Guide> = mutableListOf(
        Guide(1, "Энциклопедия", "Сборник математических определений, теорем и приложений"),
        Guide(2, "Тесты", "Интерактивные экзамены и тесты по темам"),
        Guide(
            3,
            "Desmos",
            "Веб-приложение, строющее график функции и помогающее постичь математику"
        ),
        Guide(
            4,
            "Сопроводительные материалы",
            "Подготовка к экзаменам и дополнения к текущим лекциям"
        ),
        Guide(5, "Конвертер изображений", "Конвертация изображения в формат pdf"),
        Guide(
            6,
            "Примечания",
            "Примечания, литература, использование, авторское право, отказ от ответственности"
        ),
        Guide(7, "Авторы", "Управление проектами и авторы"),
        Guide(8, "Контакты", "Контакты с авторами проекта")
    )

}