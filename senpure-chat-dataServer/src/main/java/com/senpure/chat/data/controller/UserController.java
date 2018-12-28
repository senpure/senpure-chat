package com.senpure.chat.data.controller;

import com.senpure.base.spring.BaseController;
import com.senpure.chat.data.criteria.ChangeDiamondCriteria;
import com.senpure.chat.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * UserDiamondController
 *
 * @author senpure
 * @time 2018-12-28 16:13:43
 */
@Controller
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("user/diamond")
    @ResponseBody
    public Object updateUserDiamond(HttpServletRequest request, @Valid @ModelAttribute("criteria") ChangeDiamondCriteria criteria, BindingResult validResult) {

        if (validResult.hasErrors()) {
            return incorrect(request, validResult);
        }
        userService.addDiamond(criteria.getType(), criteria.getUserId(), criteria.getDiamond());
        return null;
    }
}
