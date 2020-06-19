package backend

import grails.gorm.transactions.Transactional

@Transactional
class StockService {

    static scope = "session"

    def list() {
        def list = Stock.list()
        return list
    }

    def getStockId(long id){
        def stock = Stock.get(id)
        return stock
    }

    def getStocks(Company company){
        def stocks = company.getStocks()
    }

}
