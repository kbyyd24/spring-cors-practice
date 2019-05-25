package cn.gaoyuexiang.spring.practice.cors.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http?.cors()
                ?.and()
                ?.authorizeRequests()
                ?.antMatchers("/**")
                ?.authenticated()
                ?.anyRequest()
                ?.denyAll()
                ?.and()
                ?.httpBasic()
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.inMemoryAuthentication()
                ?.withUser("cors-tester")
                ?.password("tester")
                ?.roles("CORS_TESTER")
                ?.and()
                ?.passwordEncoder(DemoPasswordEncoder)
    }

}

object DemoPasswordEncoder : PasswordEncoder {
    override fun encode(rawPassword: CharSequence?): String = rawPassword.toString()

    override fun matches(rawPassword: CharSequence?, encodedPassword: String?): Boolean = rawPassword == encodedPassword
}