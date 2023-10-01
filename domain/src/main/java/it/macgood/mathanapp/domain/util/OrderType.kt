package it.macgood.mathanapp.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
