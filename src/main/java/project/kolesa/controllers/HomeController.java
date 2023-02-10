package project.kolesa.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.kolesa.dto.AdDto;
import project.kolesa.dto.CommentDto;
import project.kolesa.dto.UserDto;
import project.kolesa.model.*;
import project.kolesa.repository.AdRepository;
import project.kolesa.services.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class HomeController {
    private AdService adService;
    private UserService userService;
    private CityService cityService;
    private AdRepository adRepository;
    private ColorService colorService;
    private EngineTypeService engineTypeService;
    private BrandService brandService;
    private CommentService commentService;
    private FileUploadService fileUploadService;
    private CarBodyService carBodyService;
    private GearboxService gearboxService;
    private ModelService modelService;


    @GetMapping(value = "/")
    public String indexPage(Model model) {
        List<Ad> ad = adService.getAllAd();
        List<City> city = cityService.getAllCity();
        model.addAttribute("city", city);
        List<Brand> brand = brandService.getAllBrand();
        model.addAttribute("brand", brand);
        List<EngineType> engineType = engineTypeService.getAllEngineType();
        model.addAttribute("engineType", engineType);
        List<Color> color = colorService.getAllColor();
        model.addAttribute("color", color);
        List<CarBody> carBody = carBodyService.getAllCarBody();
        model.addAttribute("carBody", carBody);
        List<Gearbox> gearbox = gearboxService.getAllGearbox();
        model.addAttribute("gearbox", gearbox);
        List<project.kolesa.model.Model> models = modelService.getAllModel();
        model.addAttribute("model", models);
        model.addAttribute("car", ad);
        return "index";
    }

    @GetMapping(value = "/enter")
    public String enterPage() {
        return "enter";
    }

    @GetMapping(value = "/profile")
    @PreAuthorize("isAuthenticated()")
    public String profilePage() {
        return "profile";
    }

    @GetMapping(value = "/adminpanel")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String adminPnelPage() {
        return "admin";
    }

    @GetMapping(value = "/editorpanel")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR')")
    public String editorPage() {
        return "editor";
    }

    @GetMapping(value = "/403")
    public String deniedPage() {
        return "403";
    }

    @GetMapping(value = "/signup")
    public String signUpPage() {
        return "signup";
    }

    @PostMapping(value = "/signup")
    public String signUp(UserDto userDto) {
        if (userService.registerUser(userDto)) {
            return "redirect:/signup?success";
        }
        return "redirect:/signup?error";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/updatepassword")
    public String updatepassword() {
        return "updatepassword";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/updatepassword")
    public String updatePssword(@RequestParam(name = "old_password") String oldPassword,
                                @RequestParam(name = "new_password") String newPassword,
                                @RequestParam(name = "re_new_password") String reNewPassword) {
        userService.updatePassword(oldPassword, newPassword, reNewPassword);
        return "redirect:/profile";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/adAdd")
    public String adAdd(AdDto adDto) {
        adService.addAd(adDto);
        return "/adAdd";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/adAdd")
    public String adadd(Model model) {
        List<City> city = cityService.getAllCity();
        model.addAttribute("city", city);
        List<Brand> brand = brandService.getAllBrand();
        model.addAttribute("brand", brand);
        List<EngineType> engineType = engineTypeService.getAllEngineType();
        model.addAttribute("engineType", engineType);
        List<Color> color = colorService.getAllColor();
        model.addAttribute("color", color);
        List<CarBody> carBody = carBodyService.getAllCarBody();
        model.addAttribute("carBody", carBody);
        List<Gearbox> gearbox = gearboxService.getAllGearbox();
        model.addAttribute("gearbox", gearbox);
        List<project.kolesa.model.Model> models = modelService.getAllModel();
        model.addAttribute("model", models);

        return "/adAdd";
    }

    @GetMapping(value = "/allad")
    public String allAd(Model model) {
        List<Ad> ad = adService.getAllAd();
        model.addAttribute("car", ad);
        return "allad";
    }

    @GetMapping(value = "/addetails/{id}")
    public String adDetails(@PathVariable(name = "id") Long id, Model model) {
        Ad ad = adService.getAd(id);
        List<Comments> comments = commentService.getByAd(ad);
        model.addAttribute("comments", comments);
        if (ad != null) {
            model.addAttribute("car", ad);
        }
        var user = userService.getCurrentUser();

        boolean isFavorite = false;
        if (user != null) {
            isFavorite = user.getFavorites().contains(ad);
        }
        model.addAttribute("isFavorite", isFavorite);

        return "addetails";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/uploadphoto")
    public String uploadPhoto(@RequestParam(name = "photo") MultipartFile[] file, AdDto adDto) {


        if (!fileUploadService.uploadPhoto(file, adDto)) {
            return "redirect:/error=Unable  to  add photo";
        }
        return "redirect:/";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/updatead/{id}")
    public String updateAd(@PathVariable Long id, Model model) {
        List<City> city = cityService.getAllCity();
        model.addAttribute("city", city);
        List<Brand> brand = brandService.getAllBrand();
        model.addAttribute("brand", brand);
        List<EngineType> engineType = engineTypeService.getAllEngineType();
        model.addAttribute("engineType", engineType);
        List<Color> color = colorService.getAllColor();
        model.addAttribute("color", color);
        List<CarBody> carBody = carBodyService.getAllCarBody();
        model.addAttribute("carBody", carBody);
        List<Gearbox> gearbox = gearboxService.getAllGearbox();
        model.addAttribute("gearbox", gearbox);
        List<project.kolesa.model.Model> models = modelService.getAllModel();
        model.addAttribute("model", models);


        Ad ad = adService.getAd(id);
        model.addAttribute("updatead", ad);


        return "updateAd";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/updatead/{id}")
    public String updatead(@PathVariable Long id, AdDto adDto) {
        Ad ad = adService.getAd(id);
        adService.updateAd(adDto, id);
        return "redirect:/updatead/" + id;
    }

    @GetMapping(value = "/viewpic/{picToken}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public @ResponseBody byte[] viewPic(@PathVariable(name = "picToken") String token) throws IOException {
        return fileUploadService.getPhoto(token);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/deletead/{id}")
    public String deleteAd(@RequestParam(name = "id") Long id) {
        adService.deleteAd(id);
        return "redirect:/myad";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/deleteComment")
    public String deleteComment(@RequestParam(name = "id") Long id) {
        commentService.deleteComment(id);
        return "redirect:/addetails/" + id;
    }

    @GetMapping(value = "/search")
    public String searchBy(@RequestParam(name = "brand.id") Optional<Long> brandOpt,
                           @RequestParam(name = "city.id") Optional<Long> cityOpt,
                           @RequestParam(name = "color.id") Optional<Long> colorOpt,
                           @RequestParam(name = "gearbox.id") Optional<Long> gearboxOpt,
                           @RequestParam(name = "carBody.id") Optional<Long> carBodyOpt,
                           @RequestParam(name = "engineType.id") Optional<Long> engineTypeOpt,
                           @RequestParam(name = "model.id") Optional<Long> modelOpt,
                           Model model) {

        var brand = brandOpt.flatMap(e -> brandService.findBrand(e));
        var city = cityOpt.flatMap(e -> cityService.findCity(e));
        var color = colorOpt.flatMap(e -> colorService.findColor(e));
        var gearbox = gearboxOpt.flatMap(e -> gearboxService.findGearbox(e));
        var carBody = carBodyOpt.flatMap(e -> carBodyService.findCarBody(e));
        var engineType = engineTypeOpt.flatMap(e -> engineTypeService.findEngineType(e));
        var models = modelOpt.flatMap(e -> modelService.findModel(e));

        List<Ad> ad = adService.search(brand, city, color, gearbox, carBody, engineType, models);
        model.addAttribute("car", ad);
        return "allad";
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/myad")
    public String myad(Model model) {
        List<Ad> ads = adRepository.findAllByAuthor(userService.getCurrentUser());
        model.addAttribute("car", ads);
        return "myad";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/addcomment")
    public String addComment(@RequestParam(name = "id") Long id,
                             CommentDto commentDto) {
        commentService.addComment(commentDto, id);
        return "redirect:/addetails/" + id;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/makeFavorite")
    public String makeFavorite(@RequestParam(name = "id") Long id) {
        userService.makeFavorite(adService.getAd(id));
        return "redirect:/addetails/" + id;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/removeFavorite")
    public String removeFavorite(@RequestParam(name = "id") Long id) {
        userService.removeFavorite(adService.getAd(id));
        return "redirect:/addetails/" + id;
    }
}