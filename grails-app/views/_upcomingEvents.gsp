<body>
<div style="text-align: center;
background-color: #333333;
color: #4e8d38;
border: .1em solid #4e8d38;
margin: 1em 1em 1em 1em;
padding: 1em;
border-radius: 0.7em;
display: block;
float: left;">
    <h1 style="color: #7ed26c;
    text-align: center;
    font-size: 1.4em;
    margin: 0 0 0.3em;
    padding: 0.2em;">Events happening this week</h1>

    <table style="display: block;
    width: auto;
    overflow-x: auto;
    max-width: 100%;
    margin: 1.5em 0.5em 1.5em 0.5em;
    border-radius: .5em;
    color: #7ed26c;
    background-color: #142d04;">
        <thead>
        <tr style="border: 0;">
            <th style="padding: .75rem;
            vertical-align: top;
            border-top: 1px solid #dee2e6">Name</th>
            <th style="padding: .75rem;
            vertical-align: top;
            border-top: 1px solid #dee2e6">Location</th>
            <th style="padding: .75rem;
            vertical-align: top;
            border-top: 1px solid #dee2e6">Start Date</th>
            <th style="padding: .75rem;
            vertical-align: top;
            border-top: 1px solid #dee2e6">End Date</th>
            <th style="padding: .75rem;
            vertical-align: top;
            border-top: 1px solid #dee2e6">Description</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${events}" var="event">
            <tr>
                <td style="padding: .75rem;
                vertical-align: top;
                border-top: 1px solid #dee2e6"><g:link url="http://localhost:8080/TekDays/tekEvent/show/${event.id}">
                    ${event.name}</g:link></td>
                <td style="padding: .75rem;
                vertical-align: top;
                border-top: 1px solid #dee2e6">${event.city}, ${event.venue}</td>
                <td style="padding: .75rem;
                vertical-align: top;
                border-top: 1px solid #dee2e6">${event.startDate.format("EEE, d MMM")}</td>
                <td style="padding: .75rem;
                vertical-align: top;
                border-top: 1px solid #dee2e6">${event.endDate.format("EEE, d MMM")}</td>
                <td style="padding: .75rem;
                vertical-align: top;
                border-top: 1px solid #dee2e6">${event.description}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
</body>
