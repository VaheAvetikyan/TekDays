<h3><g:message code="sponsor.sponsorships.label"/></h3>
<table>
    <thead>
    <tr>
        <th><g:message code="sponsor.name.label"/></th>
        <th><g:message code="sponsor.website.label"/></th>
        <th><g:message code="sponsorship.contributionType.label"/></th>
    </tr>
    </thead>
    <g:each in="${sponsorships}" var="s">
        <tr>
            <td>
                <g:link controller="sponsor" action="show" id="${s.sponsor.id}">
                    ${s.sponsor.name}
                </g:link>
            </td>
            <td>
                <a href="${s.sponsor.website}">
                    ${s.sponsor.website}
                </a>
            </td>
            <td>
                ${s.contributionType}
            </td>
        </tr>
    </g:each>
</table>
