package com.ttnd.linksharing

import CO.UserCO
import VO.PostVO
import com.ttnd.linksharing.constants.Constants

import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityUtils

import javax.servlet.http.HttpServletResponse

import org.springframework.security.access.annotation.Secured
import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.authentication.CredentialsExpiredException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.LockedException
import org.springframework.security.core.context.SecurityContextHolder as SCH
import org.springframework.security.web.WebAttributes

@Secured(['permitAll'])
class LoginController {

    def mailService
    def authenticationTrustResolver
    def springSecurityService

    def index() {
        /*if (session.user) {
            forward(controller: "user", action: "index")
        } else {

            List<PostVO> topPosts = Resource.getTopPosts()
            List<PostVO>recentPosts = Resource.getRecentPosts()
            render (view: "index", model: [topPosts : topPosts, recentPosts: recentPosts])
        }*/
        if (springSecurityService.isLoggedIn()) {
            redirect uri: SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl
        }
        else {
            redirect action: 'auth', params: params
        }
    }

    def auth() {

        def config = SpringSecurityUtils.securityConfig

        if (springSecurityService.isLoggedIn()) {
            redirect uri: config.successHandler.defaultTargetUrl
            return
        }

        List<PostVO> topPosts = Resource.getTopPosts()
        List<PostVO>recentPosts = Resource.getRecentPosts()
        String view = '/login/index'
        String postUrl = "${request.contextPath}${config.apf.filterProcessesUrl}"
        render view: view, model: [postUrl: postUrl,
                                   rememberMeParameter: config.rememberMe.parameter, topPosts : topPosts, recentPosts: recentPosts]
    }

    def authAjax() {
        response.setHeader 'Location', SpringSecurityUtils.securityConfig.auth.ajaxLoginFormUrl
        response.sendError HttpServletResponse.SC_UNAUTHORIZED
    }

    def denied() {
        if (springSecurityService.isLoggedIn() &&
                authenticationTrustResolver.isRememberMe(SCH.context?.authentication)) {
            // have cookie but the page is guarded with IS_AUTHENTICATED_FULLY
            redirect action: 'full', params: params
        }
    }


    def full() {
        def config = SpringSecurityUtils.securityConfig
        render view: 'auth', params: params,
                model: [hasCookie: authenticationTrustResolver.isRememberMe(SCH.context?.authentication),
                        postUrl: "${request.contextPath}${config.apf.filterProcessesUrl}"]
    }


    def authfail() {

        String msg = ''
        def exception = session[WebAttributes.AUTHENTICATION_EXCEPTION]
        if (exception) {
            if (exception instanceof AccountExpiredException) {
                msg = g.message(code: "springSecurity.errors.login.expired")
            }
            else if (exception instanceof CredentialsExpiredException) {
                msg = g.message(code: "springSecurity.errors.login.passwordExpired")
            }
            else if (exception instanceof DisabledException) {
                msg = g.message(code: "springSecurity.errors.login.disabled")
            }
            else if (exception instanceof LockedException) {
                msg = g.message(code: "springSecurity.errors.login.locked")
            }
            else {
                msg = g.message(code: "springSecurity.errors.login.fail")
            }
        }

        if (springSecurityService.isAjax(request)) {
            render([error: msg] as JSON)
        }
        else {
            flash.message = msg
            redirect action: 'auth', params: params
        }
    }

    def ajaxSuccess() {
        render([success: true, username: springSecurityService.authentication.name] as JSON)
    }

    def ajaxDenied() {
        render([error: 'access denied'] as JSON)
    }


/*
    def loginHandler(String username, String password) {

        User user = User.findByUsernameAndPassword(username, password)

        if (user) {
            if (user.isActive) {
                session.user = user
            } else {
                flash.loginError = g.message(code: "not.active.user")
            }
        } else {
            flash.loginError = g.message(code: "not.found.user")
        }

        redirect(action: "index")
    }
*/

    def register(UserCO user) {

        List<PostVO> topPosts = Resource.getTopPosts()
        List<PostVO>recentPosts = Resource.getRecentPosts()

        if(user.hasErrors())
        {
            flash.error = g.message(code: "not.valid.user")

            render (view: "index", model: [topPosts : topPosts, recentPosts: recentPosts, user: user])
        }
        else
        {
            User newUser = user.properties
            newUser.isActive = true
            newUser.isAdmin = false

            if(!params.pic.empty)
            {
                if(Constants.IMAGE_CONTENT_TYPE.contains(params.pic.contentType))
                    newUser.photo = params.pic.bytes
            }

            if(newUser.saveInstance())
            {
                log.info "User saved successfully"
                session.user = newUser
                redirect(controller: "user", action: "index")
            }
            else{
                flash.error = g.message(code: "not.saved.user")
                render (view: "index", model: [topPosts : topPosts, recentPosts: recentPosts, user: newUser])
            }
        }
    }

    def logout() {
        session.invalidate()
        redirect(action:'index')
    }

    def validateEmail(){

        Integer numUser = User.countByEmailID(params.emailID)

        Boolean result = numUser ? false : true

        render result
    }

    def validateUsername(){

        Integer numUser = User.countByUsername(params.username)
        Boolean result = numUser ? false : true

        render result
    }

    def validateUsernameForLogin(){

        Integer numUser = User.countByUsername(params.username)
        Boolean result = numUser ? true : false

        render result
    }

}
