package backend

import javax.xml.bind.ValidationException
import java.time.LocalDateTime

class CompanyController {

    def companyService

    static responseFormats = ['json']

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        respond companyService.list()
    }

    def show(Long id){
        respond companyService.get(id)
    }

    def save(Company company) {
        companyService.save(company)
    }

    def delete(Long id) {
        companyService.delete(id)
        redirect action:"index", method:"GET"
    }
}
