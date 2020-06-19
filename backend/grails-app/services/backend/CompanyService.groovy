package backend

import grails.gorm.transactions.Transactional
import java.time.LocalDateTime

@Transactional
class CompanyService {

    StockService stockService = new StockService()
    static scope = "session"
    Double initialPrice = 1.00d

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
        def listCompanies = list()
        for (int i = 0; i < listCompanies.size(); i++) {
            listCompanies[i].addToStocks(new Stock(price: this.initialPrice, datePrice: LocalDateTime.now()))
            listCompanies[i].save()
        }
        this.initialPrice += 0.02
        if (this.initialPrice.equals(2.00)){
            this.initialPrice = 1.00d
        }
    }

    def save(Company company){
        Double price = 1.00
        company = new Company(
                name: company.name,
                segment: company.segment
        )
        company.addToStocks(new Stock(price: price + 0.10d, datePrice: LocalDateTime.now()))
        company.save()
    }

    def delete(id){
        Company.get(id).delete()
    }

}
