package com.example.steam.controller;

import com.example.steam.entity.*;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.SpikeGameKey;
import com.example.steam.service.*;
import com.example.steam.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-18
 * @time: 22:18
 */
@Controller
public class PageController {

    Logger log= LoggerFactory.getLogger(PageController.class);

    @Autowired
    TypeService typeService;
    @Autowired
    GameService gameService;
    @Autowired
    ImageService imageService;
    @Autowired
    UserGameService userGameService;
    @Autowired
    RedisService redisService;
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;
    @Autowired
    ShoppingCartService shoppingCartService;
    @Autowired
    SpikeGameService spikeGameService;
    @Autowired
    LabelService labelService;

    @Autowired
    SensitiveWordService sensitiveWordService;


    @RequestMapping("/")
    public String index(LoginUser loginUser, Model model){
        model.addAttribute("user",loginUser);
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/classification/{typeName}")
    public String classify(LoginUser loginUser,
                           @PathVariable("typeName")String typeName,
                           Model model){
        log.info(typeName);
        model.addAttribute("typeName",typeName);
        model.addAttribute("user",loginUser);
        return "classlist";
    }

    @RequestMapping("/detail/{gameId}")
    public String detail(LoginUser loginUser,
                         @PathVariable("gameId")long gameId,
                         Model model){
        Boolean contains=null;
        if (loginUser!=null){
            log.error(loginUser.toString());
            contains=userGameService.isContains(loginUser.getEmail(),gameId);
        }
        SpikeGameDetail spikeGameDetail=redisService.get(SpikeGameKey.SPIKE_ID,gameId+"",SpikeGameDetail.class);
        model.addAttribute("spike",spikeGameDetail);
        model.addAttribute("gameId",gameId);
        model.addAttribute("user",loginUser);
        model.addAttribute("contains",contains);
        return "gamedetail";
    }

    @RequestMapping("/cart")
    public String cart(LoginUser loginUser,
                       Model model){
        if (loginUser==null){
            return "login";
        }
        model.addAttribute("user",loginUser);
        return "shoppingcart";
    }

    @RequestMapping("/search")
    public String searchResult(LoginUser loginUser,
                               @RequestParam("content")String content,
                               Model model){
        model.addAttribute("content",content);
        model.addAttribute("user",loginUser);
        return "searchresult";
    }

    @RequestMapping("/personalcenter/{email}")
    public String personalCenter(LoginUser loginUser,
                                 @PathVariable("email")String email,
                                 Model model){
        model.addAttribute("user",loginUser);
        model.addAttribute("email",email);
        return "personalcenter";
    }



    @RequestMapping("/editpersonal")
    public String editPersonal(LoginUser loginUser,
                               Model model){
        if (loginUser==null){
            return "login";
        }
        model.addAttribute("user",loginUser);
        return "editpersonal";
    }

    @RequestMapping("/showallgame")
    public String showAllGame(LoginUser loginUser,Model model){
        if (loginUser==null){
            return "login";
        }
        model.addAttribute("user",loginUser);
        return "showallgame";
    }

    @RequestMapping("/showallcomment")
    public String showAllComment(LoginUser loginUser,Model model){
        if (loginUser==null){
            return "login";
        }
        model.addAttribute("user",loginUser);
        return "showallcomment";
    }

    @RequestMapping("/admin/login")
    public String adminLogin(){
        return "admin/login";
    }

    @RequestMapping("/admin/index")
    public String adminIndex(){
        return "admin/index";
    }

    @RequestMapping("/admin/game-edit/{gameId}")
    public String adminEditGames(@PathVariable("gameId")long gameId,
                                 Model model){
        GameDetail gameDetail=gameService.findGameById(gameId);
        List<String> typeList=typeService.findAllType();
        model.addAttribute("typeList",typeList);
        model.addAttribute("game",gameDetail);
        return "admin/game-edit";
    }

    @RequestMapping("/admin/game-list")
    public String adminShowAllGames(AdminUser adminUser,
                                    Model model){
        if (adminUser == null){
            return "/admin/login";
        }
        model.addAttribute("adminUser",adminUser);
        return "admin/game-list";
    }

    @RequestMapping("/admin/not-game-list")
    public String adminShowNotAllGames(AdminUser adminUser,
                                       Model model){
        model.addAttribute("adminUser",adminUser);
        return "admin/notissued-game-list";
    }

    @RequestMapping("/admin/game-add")
    public String adminGameAdd(Model model){
        List<String> typeList=typeService.findAllType();
        model.addAttribute("typeList",typeList);
        return "admin/game-add";
    }

    @RequestMapping("/admin/game-image-edit/{gameId}")
    public String adminGameImageEdit(@PathVariable("gameId")long gameId,
                                     Model model){
        model.addAttribute("gameId",gameId);
        return "admin/game-image-edit";
    }

    @RequestMapping("/admin/user-list")
    public String adminUserList(AdminUser adminUser,
                                Model model){
        List<UserVo> userVoList=userService.findAllUser();
        model.addAttribute("adminUser",adminUser);
        model.addAttribute("userList",userVoList);
        return "admin/member-list";
    }

    @RequestMapping("/admin/member-add")
    public String adminUserAdd(Model model){
        return "admin/member-add";
    }

    @RequestMapping("/admin/member-edit/{email}")
    public String adminUserEdit(Model model,
                                @PathVariable("email")String email){
        User user=userService.findByEmail(email);
        model.addAttribute("user",user);
        return "admin/member-edit";
    }

    @RequestMapping("/admin/change-password/{email}")
    public String adminEditPass(Model model,
                                @PathVariable("email")String email){
        User user=userService.findByEmail(email);
        model.addAttribute("user",user);
        return "admin/change-password";
    }

    @RequestMapping("/admin/allcomment/{page}")
    public String adminShowComment(@PathVariable("page")long page,
                                    AdminUser adminUser,
                                    Model model){
        List<CommentDetail> commentDetailList=commentService.findALlCommentDetailByTime();
        model.addAttribute("commentList",commentDetailList);
        model.addAttribute("adminUser",adminUser);
        return "admin/feedback-list";
    }

    @RequestMapping("/admin/edit-comment/{commentId}")
    public String adminEditComment(@PathVariable("commentId")long commentId,
                                   Model model){
        model.addAttribute("commentId",commentId);
        return "admin/comment-edit";
    }

    @RequestMapping("/admin/shoppingcart")
    public String adminShoppingCart(AdminUser adminUser,
                                    Model model){
        List<ShoppingCartDetail> shoppingCartList=shoppingCartService.findAllCart();
        model.addAttribute("shopcartList",shoppingCartList);
        model.addAttribute("adminUser",adminUser);
        return "admin/shoppingcart-list";
    }

    @RequestMapping("/admin/spike")
    public String adminSpikeShow(AdminUser adminUser,
                                 Model model){
        List<SpikeGameDetail> spikeGameDetailList=spikeGameService.findAllSpikeGameDetail();
        model.addAttribute("spikeList",spikeGameDetailList);
        model.addAttribute("adminUser",adminUser);
        return "admin/spike-list";
    }

    @RequestMapping("/admin/spike-edit/{spikeId}")
    public String adminSPikeEdit(@PathVariable("spikeId")long spikeId,
                                 Model model){
        SpikeGameDetail spikeGameDetail=spikeGameService.findOneSpikeGameDetail(spikeId);
        model.addAttribute("spikeGame",spikeGameDetail);
        return "admin/spike-edit";
    }

    @RequestMapping("/admin/spike-add")
    public String adminSPikeAdd(Model model){
        List<Game> gameList=gameService.findAllGame();
        model.addAttribute("gameList",gameList);
        return "admin/spike-add";
    }

    @RequestMapping("/admin/kind-list")
    public String adminKindShow(AdminUser adminUser,
                                Model model){
        List<Type> typeList=typeService.findAllTypes();
        model.addAttribute("kindList",typeList);
        model.addAttribute("adminUser",adminUser);
        return "admin/kind-list";
    }

    @RequestMapping("/admin/label-list")
    public String adminLabelShow(AdminUser adminUser,
                                 Model model){
        List<Label> labelList=labelService.findAllLabel();
        model.addAttribute("labelList",labelList);
        model.addAttribute("adminUser",adminUser);
        return "admin/label-list";
    }

    @RequestMapping("/admin/sensitive")
    public String adminSensitive(AdminUser adminUser,
                                 Model model){
        String sensitive=sensitiveWordService.sensitiveVo();
        model.addAttribute("sensitive",sensitive);
        model.addAttribute("adminUser",adminUser);
        return "admin/sensitive";
    }

}
