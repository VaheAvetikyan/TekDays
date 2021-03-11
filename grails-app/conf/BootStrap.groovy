import com.tekdays.TekEvent

class BootStrap {

    def init = { servletContext ->
        def summit = new TekEvent(name: 'Sevan StartUp Summit',
                city: 'Sevan, Gegharkunik, Armenia',
                organizer: 'Startup Armenia',
                venue: 'Drakhtik',
                startDate: new Date('07/26/2021'),
                endDate: new Date('08/01/2021'),
                description: '''The platform attracts leading angels, 
                                venture capitalists, entrepreneurs, 
                                and policymakers willing to join 
                                their peers in an intimate setting 
                                to think, act, and transform their 
                                environment via entrepreneurship.''')
        if (!summit.save()) {
            summit.errors.allErrors.each { error ->
                println "An error occured with summit: ${error}"
            }
        }
        def marathon = new TekEvent(name: 'Yerevan Marathon',
                city: 'Yerevan, Armenia',
                organizer: 'Arm Marathon Foundation',
                venue: 'Yerevan',
                startDate: new Date('10/2/2019'),
                endDate: new Date('10/2/2019'),
                description: '''The Marathon starts in the very center of 
                                Yerevan, near Republic square. 
                                Route takes runners through beautiful 
                                central streets of Yerevan and to 
                                Hrazdan River gorge, later 
                                returning to start location.''')
        if (!marathon.save()) {
            marathon.errors.allErrors.each { error ->
                println "An error occured with marathon: ${error}"
            }
        }
    }
    def destroy = {
    }
}
