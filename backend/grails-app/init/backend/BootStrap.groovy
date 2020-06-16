package backend

import enumeration.Segments

class BootStrap {

    def init = { servletContext ->

        def ford = new Company(
                name: 'FORD',
                segment: Segments.VEHICLES,
        ).save(failOnError: true)

        def gol = new Company(
                name: 'GOL',
                segment: Segments.AIRLINES,
        ).save(failOnError: true)

        def petrobras = new Company(
                name: 'PETROBRAS',
                segment: Segments.OIL,
        ).save(failOnError: true)
    }
    def destroy = {
    }
}
