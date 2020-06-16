package backend

import enumeration.Segments

import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class BootStrap {

    LocalTime startTradeTime = LocalTime.of(18,00,00)
    LocalTime endTradeTime = LocalTime.of(19,00,00)

    long horas = ChronoUnit.MINUTES.between(startTradeTime,endTradeTime)

    Stock initialPrice = new Stock(
            price: 1.12d,
            datePrice: LocalDateTime.now()
    )

    def init = { servletContext ->

        def ford = new Company(
                name: 'FORD',
                segment: Segments.VEHICLES,
                stocks: this.initialPrice
        ).save(failOnError: true)

        def gol = new Company(
                name: 'GOL',
                segment: Segments.AIRLINES,
        ).save(failOnError: true)

        def petrobras = new Company(
                name: 'PETROBRAS',
                segment: Segments.OIL,
        ).save(failOnError: true)

//        def timer = new Timer()
//        def task = timer.runAfter(5000){
//            def currentQuota = petrobras.getPrice()
//            println currentQuota + quotePetrobras
//            petrobras.setPrice(currentQuota + quotePetrobras)
//        }
//
//        while (horas){
//            def timer = new Timer()
//            def task = timer.runAfter(60000){
//                println("FON")
//        }

    }
    def destroy = {
    }
}
