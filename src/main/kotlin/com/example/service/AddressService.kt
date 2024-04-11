package com.example.service

import com.example.dao.AddressDAO
import com.example.models.Address
import java.util.*

class AddressService (private val addressDao: AddressDAO) {

    fun getAllAddress(): List<Address> {
        return addressDao.findAll();
    }

    fun getAddressById(id: String): Address? {
        return addressDao.findById(id)
    }

    fun createAddress(address: Address) {
        address.id = UUID.randomUUID().toString()
        addressDao.create(address)
    }
}