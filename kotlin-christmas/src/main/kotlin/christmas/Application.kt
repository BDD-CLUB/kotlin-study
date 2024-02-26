package christmas

import christmas.restaurant.event.EventPlanner
import christmas.restaurant.Restaurant

fun main() {
    val eventPlanner = EventPlanner()
    val restaurant = Restaurant(eventPlanner)
    restaurant.open()
}
