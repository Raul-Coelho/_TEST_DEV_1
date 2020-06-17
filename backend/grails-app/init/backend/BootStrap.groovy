package backend

import enumeration.Segments

import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class BootStrap {

//    Check Trade Time
    LocalTime startTradeTime = LocalTime.of(18,00,00)
    LocalTime endTradeTime = LocalTime.of(19,00,00)

    long tradeTime = ChronoUnit.MINUTES.between(startTradeTime,endTradeTime)

    def init = { servletContext ->

        Stock stock = new Stock(price: 1.12d,datePrice: LocalDateTime.now())
        Stock stock2 = new Stock(price: 1.12d,datePrice: LocalDateTime.now())


        Company ford = new Company(
                name: 'FORD',
                segment: Segments.VEHICLES,
                stocks: [stock,stock2]
        )
        ford.save(failOnError: true)

//        def gol = new Company(
//                name: 'GOL',
//                segment: Segments.AIRLINES,
//                stocks: this.initialPrice
//        ).save(failOnError: true)
//
//        def petrobras = new Company(
//                name: 'PETROBRAS',
//                segment: Segments.OIL,
//                stocks: this.initialPrice
//        ).save(failOnError: true)

    }
    def destroy = {
    }
}
