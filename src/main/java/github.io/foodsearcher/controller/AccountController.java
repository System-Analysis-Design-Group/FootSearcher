package top.yaoyongdou.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.yaoyongdou.model.StatusMsg;
import top.yaoyongdou.service.UserInfoService;
import top.yaoyongdou.shiro.SecurityHelper;
import top.yaoyongdou.shiro.UserInfo;

import javax.validation.constraints.NotNull;


/**
 * Created by young on 18-3-6.
 */

@RestController
@CrossOrigin(origins = "*")
public class AccountController {

    @Autowired
    UserInfoService userInfoService;

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public StatusMsg logout() {
        System.out.println("begin logout...........");

        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
        } catch (Exception exp) {
            return StatusMsg.returnError();
        }

        System.out.println("logout...........");

        return StatusMsg.returnOk();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public StatusMsg login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {

        System.out.println("begin login................");

        AuthenticationToken token = new UsernamePasswordToken(username, password);

        Subject curentUser = SecurityUtils.getSubject();
        try {
            curentUser.login(token);
        } catch (IncorrectCredentialsException ice) {
            System.out.println("error password");
            return StatusMsg.returnError();
        } catch (UnknownAccountException uae) {
            System.out.println("never exist this username");
            return StatusMsg.returnError();
        } catch (AuthenticationException ae) {
            System.out.println("unkown error");
            return StatusMsg.returnError();
        } catch (Exception exp) {
            return StatusMsg.returnError();
        }

        System.out.println("login success");
        return StatusMsg.returnOk();

    }

    @RequestMapping(value = "/createaccount", method = RequestMethod.POST)
//    @RequiresAuthentication
    public StatusMsg createAccount(@RequestParam("username") String username,
                                   @RequestParam("password") String password,
                                   @RequestParam(value = "salt", defaultValue = "salt") String salt) {

        // 加密密码
        salt = ByteSource.Util.bytes(salt).toHex();
        String encryptPassword = SecurityHelper.encryptPassword(password, salt);

        // 生成用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setName(username);
        userInfo.setUsername(username);
        userInfo.setPassword(encryptPassword);
        userInfo.setSalt(salt);

        userInfoService.createUserInfo(userInfo);

        return StatusMsg.returnOk();

    }


    @RequestMapping(value = "changepassword", method = RequestMethod.POST)
    @RequiresAuthentication
    public StatusMsg changePassword(@RequestParam("oldPassword")@NotNull String oldPassword,
                                    @RequestParam("newPassword")@NotNull String newPassword) {

        if ("".equals(oldPassword) || "".equals(newPassword)) {
            return StatusMsg.returnError();
        }

        Subject currentUser = SecurityUtils.getSubject();
        String username = (String) currentUser.getPrincipal();
        UserInfo userInfo = userInfoService.findByUsername(username);

        if (userInfo == null) {
            return StatusMsg.returnError();
        }

        String testHash = SecurityHelper.encryptPassword(oldPassword, userInfo.getSalt());
        if (testHash.equals(userInfo.getPassword())) {
            userInfo.setPassword(SecurityHelper.encryptPassword(newPassword, userInfo.getSalt()));
            userInfoService.createUserInfo(userInfo);
            return StatusMsg.returnOk();
        }

        return StatusMsg.returnError();
    }



}
