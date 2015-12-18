package fr.cmm.controller;

import fr.cmm.domain.Recipe;
import fr.cmm.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

/**
 * Created by pomme on 18/12/2015.
 */

@Controller
@RequestMapping("/api")
public class ApiController {

    @Inject
    private RecipeService recipeService;

    //@RequestMapping("/recipes")
    //@ResponseBody
    //public List<Recipe> listePaginee(SearchForm searchForm, ModelMap model) {

    //    PageQuery pageQuery = new PageQuery();
    //    Pagination pagination = new Pagination();


     //   pagination.setPageIndex(searchForm.getPageIndex());
    //    pagination.setPageSize(pageQuery.getSize());
    //    pagination.setCount(recipeService.countByQuery(pageQuery));

    //    return liste ;

    //}
    // fonction pas finie

    @RequestMapping("/recipes/{id}")
    @ResponseBody
    public Recipe recette(@PathVariable("id") String id) {
        Recipe recipe = recipeService.findById(id);

        if (recipe == null) {
            throw new ResourceNotFoundException();
        }

        return recipe;
    }
}