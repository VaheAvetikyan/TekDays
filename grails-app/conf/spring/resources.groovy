// Place your Spring DSL code here
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

beans = {
    System.setProperty('org.hibernate.envers.audit_table_prefix', 'AUDITED_')
    System.setProperty('org.hibernate.envers.audit_table_suffix', '')
//    localeResolver(SessionLocaleResolver) {
//        defaultLocale = new Locale('hy');
//    }
}
