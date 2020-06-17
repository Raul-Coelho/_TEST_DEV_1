package backend

import enumeration.Segments

import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit

class BootStrap {

//    Check Trade Time
    LocalTime startTradeTime = LocalTime.of(15,25,00)
    LocalTime endTradeTime = LocalTime.of(15,26,00)

    long tradeTime = ChronoUnit.MINUTES.between(startTradeTime,endTradeTime)

    Stock initialQuota = new Stock(price: 1.12d,datePrice: LocalDateTime.now())

    CompanyController companyController
    CompanyService service = new CompanyService()

    def init = { servletContext ->

        service.save(new Company(
                name: 'FORD',
                segment: Segments.VEHICLES,
        ))

        service.save(new Company(
                name: 'GOL',
                segment: Segments.AIRLINES,
        ))

        service.save(new Company(
                name: 'PETROBRAS',
                segment: Segments.OIL,
        ))

    }
    def destroy = {
    }
}
