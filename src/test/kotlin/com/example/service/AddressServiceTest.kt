package com.example.service

import com.example.dao.AddressDAO
import com.example.models.Address
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import kotlin.test.assertEquals

internal class AddressServiceTest {
    private lateinit var addressService: AddressService
    private lateinit var mockAddressDao: AddressDAO

    @Before
    fun setup() {
        mockAddressDao = mock(AddressDAO::class.java)

        addressService = AddressService(mockAddressDao)
    }

    @Test
    fun testGetAllAddress_shouldFetchAllAddress() {
        `when`(mockAddressDao.findAll()).thenReturn(getMockAddress())
        val addresses = addressService.getAllAddress()
        assertEquals(addresses.size, 3)
    }

    private fun getMockAddress(): List<Address> {
        val mockAddress1 = Address(
            id = "1",
            addressLine1 = "123 Main St",
            addressLine2 = "Apt 101",
            city = "New York",
            state = "NY",
            zipcode = "10001"
        )

        val mockAddress2 = Address(
            id = "2",
            addressLine1 = "456 Elm St",
            addressLine2 = "Suite 200",
            city = "Los Angeles",
            state = "CA",
            zipcode = "90001"
        )

        val mockAddress3 = Address(
            id = "3",
            addressLine1 = "789 Oak St",
            addressLine2 = "",
            city = "Chicago",
            state = "IL",
            zipcode = "60601"
        )
        return listOf(
            mockAddress1,
            mockAddress2,
            mockAddress3
        )
    }
}