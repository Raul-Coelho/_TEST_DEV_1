package backend

import grails.rest.Resource
import groovy.transform.ToString

import java.time.LocalDateTime

@Resource(uri='/stocks')
class Stock {

    Double price
    LocalDateTime datePrice

    static belongsTo = [company: Company]

    static constraints = {
        price blank: false, nullable: false
        datePrice blank: false, nullable: false
    }

    String toString(){
        price
        datePrice
    }
}
