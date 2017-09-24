package com.founder.cms.config;

import com.founder.cms.auth.filter.SessionFilter;
import com.founder.cms.auth.filter.URLEntryFilter;
import com.founder.cms.member.service.IKsRoleService;
import com.founder.cms.member.service.IKsUserService;
import com.founder.common.support.web.FilterConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FilterConfig
 * 过滤器配置类：  为了解决 @WebFilter 不能设置顺序的问题
 *
 * @author Chensong
 * @date 2017/1/19
 */
@Configuration
public class FilterConfig extends FilterConfiguration{

    @Bean
    public FilterRegistrationBean sessionFilter(IKsUserService ksUserService, IKsRoleService ksRoleService, @Value("${proxy.server}") String adress) {
        SessionFilter filter = new SessionFilter();
        filter.setKsRoleService(ksRoleService);
        filter.setKsUserService(ksUserService);
        filter.setProdbAdress(adress);
        return this.createFilterBean(1, filter, "/e5sso/auth.do");
    }

    @Bean
    public FilterRegistrationBean urlEntryFilter(
            IKsUserService ksUserService, @Value("${drap.login.flag:true}") Boolean flag,
            @Value("${proxy.server}") String adress) {
        URLEntryFilter filter = new URLEntryFilter();
        filter.setKsUserService(ksUserService);
        filter.setFlag(flag);
        filter.setProdbAdress(adress);
        return this.createFilterBean(2, filter, "/*");
    }
}
