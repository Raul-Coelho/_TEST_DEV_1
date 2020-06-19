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
//    StockService stockService = new StockService()

    def statistics(){
        def ford = service.get(1)
        def statisticFinal = service.getStocks(ford)
        return statisticFinal
    }

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

                    println("Updating Stock Quotes: "+LocalTime.now())

                    setStartTradeTime(startTradeTime.plusHours(8))

                    println(startTradeTime)

                }else{
                    println("TradeTime CLOSED!, open at 10AM netxt day")
                    println("-----------------------------------------")
                    statistics()
                }
            }

        }, 2, 60000, TimeUnit.MILLISECONDS)
        Thread.sleep(5000)

    }

    def destroy = {
    }
}
