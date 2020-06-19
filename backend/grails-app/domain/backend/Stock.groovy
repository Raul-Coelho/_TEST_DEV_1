package backend

import grails.rest.Resource
import groovy.transform.ToString

import java.time.LocalDateTime

@Resource(uri='/stocks')
class Stock {

    Double price
    LocalDateTime datePrice

    Stock(Double price, LocalDateTime datePrice){
        this.price = price
        this.datePrice = datePrice
    }

    static hasOne = [company: Company]
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
