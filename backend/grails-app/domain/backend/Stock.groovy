package backend

import com.fasterxml.jackson.annotation.JsonFormat
import grails.rest.Resource
import java.time.LocalDate

@Resource(uri='/stocks')
class Stock {

    Double price
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    LocalDate datePrice

    Stock(Double price, LocalDate datePrice){
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
