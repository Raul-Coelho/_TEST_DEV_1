package backend

import javax.transaction.Transactional
import java.time.LocalDateTime

@Transactional
class CompanyService {

    def get(id){
        Company.get(id)
    }

    def list(){
        Company.list()
    }

    def save(Company company){
        Stock initialQuota = new Stock(price: 1.12d,datePrice: LocalDateTime.now())
        company = new Company(
                name: company.name,
                segment: company.segment
        )
        for (int i; i<3; i++){
            Double newPrice = initialQuota.getPrice() + 0.10d
            company.addToStocks(new Stock(price: newPrice,datePrice: LocalDateTime.now()))
            initialQuota.setPrice(newPrice)
        }
        company.save()
    }

    def delete(id){
        Company.get(id).delete()
    }
}
