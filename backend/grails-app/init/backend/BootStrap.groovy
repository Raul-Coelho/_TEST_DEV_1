package backend

import enumeration.Segments
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

class BootStrap {

//    Check Trade Time
    LocalTime startTradeTime = LocalTime.of(10,00,00)
    LocalTime endTradeTime = LocalTime.of(18,0,00)

    long tradeTime = ChronoUnit.SECONDS.between(startTradeTime,endTradeTime)

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

        ScheduledFuture<?> f = Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
            @Override
            void run() {
                if (startTradeTime != endTradeTime){
                    service.update()
                    println(LocalTime.now())
                    setStartTradeTime(startTradeTime.plusHours(1))
                    println(startTradeTime)
                }else{
                    println("FORA DO HORARIO COMERCIAL")
                }
            }
        }, 2, 60000, TimeUnit.MILLISECONDS)
        Thread.sleep(10000)
    }

    def destroy = {
    }
}
