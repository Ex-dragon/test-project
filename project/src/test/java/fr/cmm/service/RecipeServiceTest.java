package fr.cmm.service;

import fr.cmm.domain.Recipe;
import fr.cmm.helper.PageQuery;
import org.jongo.MongoCollection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static fr.cmm.SpringProfiles.INTEG;
import static java.util.Arrays.asList;
import static java.util.stream.StreamSupport.stream;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ImageServiceTestConfig.class)
@ActiveProfiles(INTEG)
public class RecipeServiceTest {
    @Inject
    private RecipeService recipeService;

    @Inject
    private MongoCollection recipeCollection;

    @Before
    @After
    public void clean() {
        recipeCollection.remove();
    }

    @Test
    public void save() {
        Recipe recipe = new Recipe();
        recipe.setTitle("test recipe");

        recipeService.save(recipe);

        assertEquals("test recipe", recipeCollection.findOne().as(Recipe.class).getTitle());
    }

    @Test
    public void findById() {
        Recipe recipe = new Recipe();
        recipe.setTitle("test recipe");

        recipeService.save(recipe);

        assertEquals("test recipe", recipeService.findById(recipe.getId()).getTitle());
    }

    @Test
    public void findByQuery() {
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());

        assertEquals(5, stream(recipeService.findByQuery(new PageQuery()).spliterator(), false).count());
    }

    @Test
    public void findByQueryWithCustomPageSize() {
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());

        PageQuery pageQuery = new PageQuery();
        pageQuery.setSize(2);

        assertEquals(2, stream(recipeService.findByQuery(pageQuery).spliterator(), false).count());
    }

    @Test
    public void findByQueryWithTag() {
        recipeService.save(new Recipe().withTags("tag1"));
        recipeService.save(new Recipe().withTags("tag1"));
        recipeService.save(new Recipe().withTags("tag2"));
        recipeService.save(new Recipe().withTags("tag2"));
        recipeService.save(new Recipe().withTags("tag3"));

        PageQuery pageQuery = new PageQuery();
        pageQuery.setTag("tag1");

        assertEquals(2, stream(recipeService.findByQuery(pageQuery).spliterator(), false).count());
    }

    @Test
    public void countByQueryVide() {
        assertEquals(0, recipeService.countByQuery(new PageQuery()));
    }

    @Test
    public void countByQueryPlein() {
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());

        assertEquals(5, recipeService.countByQuery(new PageQuery()));
    }

    @Test
    public void countByQueryPleintag() {
        recipeService.save(new Recipe().withTags("tag1"));
        recipeService.save(new Recipe().withTags("tag1"));
        recipeService.save(new Recipe().withTags("tag2"));
        recipeService.save(new Recipe().withTags("tag2"));
        recipeService.save(new Recipe().withTags("tag3"));

        PageQuery pageQuery = new PageQuery();
        pageQuery.setTag("tag1");

        assertEquals(2, recipeService.countByQuery(pageQuery));
    }

    @Test
    public void findAllTags() {
        recipeService.save(new Recipe().withTags("tag1", "tag2"));
        recipeService.save(new Recipe().withTags("tag2", "tag3"));

        assertEquals(asList("tag1", "tag2", "tag3"), recipeService.findAllTags());
    }

    @Test
    public void findByIdWithInvalidId() {
        String id = "56375619d4c603aa4eb412";

        assertEquals(recipeService.findById(id), null);


    }

}