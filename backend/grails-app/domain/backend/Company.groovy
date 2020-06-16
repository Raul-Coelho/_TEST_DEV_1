package backend

import groovy.transform.ToString
import java.time.LocalDateTime

@ToString
class Company {

    String name
    String segment
    Double price
    LocalDateTime datePrice

    Company(){
        this.price = 1.12d
        this.datePrice = LocalDateTime.now()
    }

    static constraints = {
        name blank: false
        segment inList: ['AIRLINES', 'VEHICLES', 'OIL']
        price blank: true, nullable: true
        datePrice blank: true, nullable: true
    }
}
