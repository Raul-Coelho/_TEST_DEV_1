package backend

import grails.gorm.transactions.Transactional
import java.time.LocalDateTime

@Transactional
class CompanyService {

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
        println("Updating Stock Quotes")
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
