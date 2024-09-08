package com.karzek.restaurants.data

import com.karzek.core.coroutines.DispatcherProvider
import com.karzek.core.error.ErrorEntityFactory
import com.karzek.core.mapper.Mapper
import com.karzek.core.result.Result
import com.karzek.domain.restaurants.NoRestaurantsFoundError
import com.karzek.domain.restaurants.Restaurant
import com.karzek.domain.restaurants.RestaurantRepository
import com.karzek.domain.restaurants.VenueSectionNotFoundError
import com.karzek.restaurants.data.api.RestaurantPageDto.VenueSectionItemDto
import com.karzek.restaurants.data.api.RestaurantsApi
import com.karzek.restaurants.data.error.RestaurantsNotFound
import com.karzek.restaurants.data.error.VenueSectionNotFound
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

class RestaurantRepositoryImplTest {

  private val api: RestaurantsApi = mock()
  private val mapper: Mapper<VenueSectionItemDto, Restaurant> = mock()
  private val dispatcher: DispatcherProvider = TestDispatcherProvider()
  private val errorEntityFactory: ErrorEntityFactory = mock()

  private val repository: RestaurantRepository = RestaurantRepositoryImpl(
    api = api,
    mapper = mapper,
    dispatcher = dispatcher,
    errorEntityFactory = errorEntityFactory,
  )

  @Test
  fun `WHEN getRestaurants is successful THEN expected result is returned`() = runTest {
    whenever(api.getRestaurants(testLatitude, testLongitude)) doReturn testRestaurantPageDto
    whenever(mapper.convert(testRestaurantPageDto.restaurantDtoAt(0))) doReturn testRestaurants[0]
    whenever(mapper.convert(testRestaurantPageDto.restaurantDtoAt(1))) doReturn testRestaurants[1]
    whenever(mapper.convert(testRestaurantPageDto.restaurantDtoAt(2))) doReturn testRestaurants[2]

    val result = repository.getRestaurants(testLatitude, testLongitude, testLimit)

    assert(result is Result.Success)
    assert((result as Result.Success).data.size == 3)
    assert(result.data[0] == testRestaurants[0])
    assert(result.data[1] == testRestaurants[1])
    assert(result.data[2] == testRestaurants[2])
    verify(mapper, times(3)).convert(any())
    verifyNoMoreInteractions(mapper)
  }

  @Test
  fun `WHEN getRestaurants fails due to no venue section found THEN expected error is returned`() =
    runTest {
      whenever(
        api.getRestaurants(
          testLatitude,
          testLongitude
        )
      ) doReturn testRestaurantPageDtoNoVenues
      whenever(errorEntityFactory.toError(any())) doReturn VenueSectionNotFoundError(
        VenueSectionNotFound()
      )

      val result = repository.getRestaurants(testLatitude, testLongitude, testLimit)

      assert(result is Result.Error)
      assert((result as Result.Error).error is VenueSectionNotFoundError)
      verifyNoInteractions(mapper)
    }

  @Test
  fun `WHEN getRestaurants fails due to no restaurants found THEN expected error is returned`() =
    runTest {
      whenever(
        api.getRestaurants(
          testLatitude,
          testLongitude
        )
      ) doReturn testRestaurantPageDtoNoRestaurants
      whenever(errorEntityFactory.toError(any())) doReturn NoRestaurantsFoundError(
        RestaurantsNotFound()
      )

      val result = repository.getRestaurants(testLatitude, testLongitude, testLimit)

      assert(result is Result.Error)
      assert((result as Result.Error).error is NoRestaurantsFoundError)
      verifyNoInteractions(mapper)
    }

}