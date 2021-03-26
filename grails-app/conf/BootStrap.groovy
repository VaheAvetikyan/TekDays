import com.tekdays.*

class BootStrap {

    def init = { servletContext ->
        if (!TekEvent.get(1)) {
            new TekUser(fullName: 'Vahe Avetikyan',
                    userName: 'VaheA',
                    password: 't0ps3cr3t',
                    email: 'vahe@mail.com',
                    website: 'https://www.blog.vahe.com',
                    bio: '''Vahe has been programming for over 1.5 years. He has
                        worked with every programming language known to man.''').save()

            new TekUser(fullName: 'Arm Marathon Foundation',
                    userName: 'ArmMarathon',
                    password: 't0ps3cr3t',
                    email: 'info@armmarathon.org',
                    website: 'https://www.armeniamarathon.org',
                    bio: '''Arm Marathon Foundation organizes a number of running
                        events in Armenia throughout the year.''').save()

            new TekUser(fullName: 'Startup Armenia',
                    userName: 'StartupArm',
                    password: 't0ps3cr3t',
                    email: 'info@startupfund.am',
                    website: 'https://www.startuparmenia.am',
                    bio: '''The mission of Startup Armenia Foundation is to support
                        startups and founders at all stages of startup development.''').save()

            def summit = new TekEvent(name: 'Sevan StartUp Summit',
                    city: 'Sevan',
                    organizer: TekUser.findByFullName('Startup Armenia'),
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
                    city: 'Yerevan',
                    organizer: TekUser.findByFullName('Arm Marathon Foundation'),
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

            def g1 = TekEvent.findByName('Sevan StartUp Summit')
            g1.addToVolunteers(new TekUser(fullName: 'Sarah Martin',
                    userName: 'sarah',
                    password: '54321',
                    email: 'sarah@martinworld.com',
                    website: 'https://www.martinworld.com',
                    bio: 'Web designer and Grails afficianado.'))
            g1.addToVolunteers(new TekUser(fullName: 'Bill Smith',
                    userName: 'Mr_Bill',
                    password: '12345',
                    email: 'mrbill@email.com',
                    website: 'https://www.mrbillswebsite.com',
                    bio: 'Software developer, claymation artist.'))

            g1.addToRespondents('ben@grailsmail.com')
            g1.addToRespondents('zachary@linuxgurus.org')
            g1.addToRespondents('solomon@bootstrapwelding.com')

            g1.save()

            def s1 = new Sponsor(name: 'Contegix',
                    website: 'http://www.contegix.com',
                    description: 'Beyond Managed Hosting for your Enterprise ').save()

            def s2 = new Sponsor(name: 'Object Computing Incorporated',
                    website: 'http://ociweb.com',
                    description: 'An OO Software Engineering Company').save()

            def s3 = new Sponsor(name: 'SOFS',
                    website: 'http://www.sofs.com',
                    description: 'IT company ').save()

            def sp1 = new Sponsorship(event: g1,
                    sponsor: s1,
                    contributionType: 'Other',
                    description: 'Cool T-Shirts').save()
            def sp2 = new Sponsorship(event: g1,
                    sponsor: s2,
                    contributionType: 'Venue',
                    description: 'Will be paying for the Moscone ').save()
        }
    }

    def destroy = {
    }
}
