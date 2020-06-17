package backend

import grails.rest.Resource
import groovy.transform.ToString

@ToString
class Company {

    String name
    String segment

    static hasMany = [stocks: Stock]

    static constraints = {
        name blank: false
        segment inList: ['AIRLINES', 'VEHICLES', 'OIL']
    }
}
