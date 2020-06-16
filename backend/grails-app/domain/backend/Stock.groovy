package backend

import groovy.transform.ToString

import java.time.LocalDateTime

@ToString
class Stock {

    Double price
    LocalDateTime datePrice

    static constraints = {
        price blank: false, nullable: false
        datePrice blank: false, nullable: false
    }
}
