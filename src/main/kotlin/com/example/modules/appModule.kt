package com.example.modules

import com.example.dao.AddressDAO
import com.example.dao.OrderDAO
import com.example.service.AddressService
import com.example.service.OrderService
import org.koin.dsl.module

val appModule = module{
    single { OrderDAO }
    single { OrderService(get()) }
    single { AddressDAO() }
    single { AddressService(get()) }
}