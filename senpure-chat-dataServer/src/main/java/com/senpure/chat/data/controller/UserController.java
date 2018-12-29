package com.senpure.chat.data.controller;

import com.senpure.base.result.ResultData;
import com.senpure.base.result.ResultMap;
import com.senpure.base.spring.BaseController;
import com.senpure.chat.data.criteria.ChangeDiamondCriteria;
import com.senpure.chat.data.entity.UserEntity;
import com.senpure.chat.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    @PutMapping("user/diamond")
    @ResponseBody
    public ResultData<UserEntity> updateUserDiamond(HttpServletRequest request, @Valid @ModelAttribute("criteria") ChangeDiamondCriteria criteria, BindingResult validResult) {

        if (validResult.hasErrors()) {
            // return incorrect(request, validResult);
            return null;
        }
        userService.addDiamond(criteria.getType(), criteria.getUserId(), criteria.getDiamond());
        return null;
    }

    @PutMapping("user/diamond2")
    @ResponseBody
    public ModelAndView updateUserDiamond2(HttpServletRequest request, @Valid @ModelAttribute("criteria") ChangeDiamondCriteria criteria, BindingResult validResult) {


        return null;
    }

    @PutMapping("user/diamond3")
    @ResponseBody
    public ResultMap updateUserDiamond3(HttpServletRequest request, @Valid @ModelAttribute("criteria") ChangeDiamondCriteria criteria, BindingResult validResult) {

        return null;
    }
}
