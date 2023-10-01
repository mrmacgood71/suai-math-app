package it.macgood.mathanapp.domain.util

sealed class TaskOrder(val orderType: OrderType) {
    class Id(orderType: OrderType): TaskOrder(orderType)
    class QuestionNumber(orderType: OrderType): TaskOrder(orderType)
}