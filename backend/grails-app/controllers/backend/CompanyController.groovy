package backend

import javax.xml.bind.ValidationException
import java.time.LocalDateTime

class CompanyController {

    def companyService

    static responseFormats = ['json']

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def show() {
        respond companyService.list()
    }

    def read(Long id){
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
