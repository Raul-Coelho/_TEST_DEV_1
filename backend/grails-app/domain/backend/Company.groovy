package backend

import grails.rest.Resource
import groovy.transform.ToString

@ToString
@Resource(uri='/companies')

class Company{

    String name
    String segment

    Company(String name, String segment, Stock stocks){
        this.name = name
        this.segment = segment
        this.stocks = stocks
    }

    static hasMany = [stocks: Stock]

    static constraints = {
        name blank: false
        segment inList: ['AIRLINES', 'VEHICLES', 'OIL']
    }

}
