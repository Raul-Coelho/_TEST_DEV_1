package backend

class StocksController {

    def stockService

    static responseFormats = ['json']

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def show() {
        respond stockService.list()
    }

    def read(Long id){
        respond stockService.get(id)
    }
}
