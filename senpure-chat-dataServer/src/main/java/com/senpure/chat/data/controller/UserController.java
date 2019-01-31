package com.senpure.chat.data.controller;

import com.senpure.base.result.ActionResult;
import com.senpure.base.result.ResultMap;
import com.senpure.base.spring.BaseController;
import com.senpure.chat.data.criteria.ChangeDiamondCriteria;
import com.senpure.chat.data.result.UserRecordResult;
import com.senpure.chat.data.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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


    private UserService userService;

    @Autowired
    public UserController setUserService(UserService userService) {
        this.userService = userService;
        return this;
    }

    @PutMapping("user/{id}/diamond")
    @ResponseBody
    @ApiImplicitParams({@ApiImplicitParam(name = "id", paramType = "path")})
    @ApiResponses(@ApiResponse(code = 200, message = "OK", response = ActionResult.class))
    public ResultMap updateUserDiamond(HttpServletRequest request, @Valid @ModelAttribute("criteria") ChangeDiamondCriteria criteria, BindingResult validResult) {
        if (validResult.hasErrors()) {
            return incorrect(request, validResult);
        }
        logger.debug("id {}", criteria.getId());
        userService.addDiamond(criteria.getType(), criteria.getId(), criteria.getDiamond());
        return ResultMap.success();
    }


    @GetMapping("user/{id}")
    @ResponseBody
    @ApiImplicitParams({@ApiImplicitParam(name = "id", paramType = "path",dataType = "long")})
    @ApiResponses(@ApiResponse(code = 200, message = "OK", response = UserRecordResult.class))
    public ResultMap readUser(HttpServletRequest request, @PathVariable  Long id) {
        Long numberId;
        try {
            numberId = Long.valueOf(id);
        } catch (NumberFormatException e) {
            logger.error("输入转换失败", e);
            return wrapMessage(request, ResultMap.notExist(), id);
        }
        return ResultMap.success();
    }

}
