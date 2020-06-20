package backend

import grails.gorm.transactions.Transactional

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Transactional
class CompanyService {

    StockService stockService = new StockService()
    static scope = "session"

    def count(){
        Company.count()
    }

    def get(id){
        def company = Company.get(id)
        return company
    }

    def list(){
        def read = Company.list()
        return read
    }

    def getStocks(Company company){
        def comp = get(company.getId())
        def stocks = comp.getStocks()

        println(
            "COMPANY:\n"+
            "   Name : "+ comp.name+"\n"+
            "   Segment: "+ comp.segment+"\n"

        )
        println(
                " NUMBER OF STOCK QUOTES: "+stocks.size()
        )
        println(
                " STOCK QUOTE: \n"
        )
        for (int i = 0; i < stocks.size() ; i++) {
            println(
                "   Price: "+stocks[i].price+"\n"+
                "   Date: "+stocks[i].datePrice+"\n"
                    )
        }
    }

    def update(){
        Double price = new Random().nextDouble() * 20
        def listCompanies = list()
        for (int i = 0; i < listCompanies.size(); i++) {
            listCompanies[i].addToStocks(new Stock(price: price, datePrice: LocalDate.now()))
            listCompanies[i].save()
        }
    }

    def save(Company company){
        Double price = new Random().nextDouble() * 20
        company = new Company(
                name: company.name,
                segment: company.segment
        )
        company.addToStocks(new Stock(price: price, datePrice: LocalDateTime.now()))
        company.save()
    }

    def delete(id){
        Company.get(id).delete()
    }

}
