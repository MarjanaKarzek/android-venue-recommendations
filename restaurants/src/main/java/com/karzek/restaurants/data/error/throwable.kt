package com.karzek.restaurants.data.error

class VenueSectionNotFound : IllegalStateException("venues section not found")
class RestaurantsNotFound : IllegalStateException("no restaurants found")