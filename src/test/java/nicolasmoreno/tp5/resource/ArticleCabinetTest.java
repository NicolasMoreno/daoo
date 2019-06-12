package nicolasmoreno.tp5.resource;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import nicolasmoreno.tp5.change.ResourceChange;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static nicolasmoreno.tp5.resource.CabinetBuilder.cabinet;
import static nicolasmoreno.tp5.resource.CabinetBuilder.newArticles;
import static nicolasmoreno.tp5.resource.ResourceBuilder.resource;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

@RunWith(JUnitQuickcheck.class)
public class ArticleCabinetTest {

    @Test
    public void detectNoChanges() {
        final ArticleCabinet cabinet = cabinet();
        final List<ResourceChange> resourceChanges = cabinet.detectChanges(newArticles());
        assertEquals("Asserting no changes", 0, resourceChanges.size());
    }

    @Property(trials = 6)
    public void detectAddingArticle(@InRange(min = "0", max = "5") Integer index) {
        final ArticleCabinet cabinet = cabinet();
        final List<Resource> newArticles = newArticles();
        final Resource newArticle = resource("H", "H");
        final Resource removedArticle = newArticles.set(index, newArticle);
        final List<ResourceChange> resourceChanges = cabinet.detectChanges(newArticles);
        assertEquals("Asserting added article", 2, resourceChanges.size());
        assertEquals("Checking same article", newArticle, resourceChanges.get(0).resource());
        assertEquals("Checking removed article", removedArticle, resourceChanges.get(1).resource());
    }

    @Property
    public void detectingAddingMoreThanOneArticle(@InRange(min = "0", max = "5") Integer index1,
                                                  @InRange(min = "0", max = "5") Integer index2) {
        assumeTrue(!index1.equals(index2));
        final ArticleCabinet cabinet = cabinet();
        final List<Resource> newArticles = newArticles();
        final Resource newArticle = resource("H", "H");
        final Resource newArticle2 = resource("X", "X");
        final Resource removedArticle = newArticles.set(index1, newArticle);
        final Resource removedArticle2 = newArticles.set(index2, newArticle2);
        final List<ResourceChange> resourceChanges = cabinet.detectChanges(newArticles);
        assertEquals("Asserting 2 added articles and 2 removed articles", 4, resourceChanges.size());
        // TODO dunno how to fully test this property

    }

    @Property
    public void detectAddingModifyArticle(@InRange(min = "0", max = "5") Integer index1,
                                          @InRange(min = "0", max = "5") Integer index2) {
        assumeTrue(!index1.equals(index2));
        final ArticleCabinet cabinet = cabinet();
        final List<Resource> newArticles = newArticles();
        final Resource newArticle = resource("H", "H");
        final Resource modifyingArticle = newArticles.set(index1, newArticle);
        final Resource removedArticle2 = newArticles.set(index2, modifyingArticle);
        final List<ResourceChange> resourceChanges = cabinet.detectChanges(newArticles);
        assertEquals("Testing same articles", newArticles, cabinet.getArticles());
        // TODO dunno how to fully test this property

    }


}

class ResourceBuilder {

    static Resource resource(String link, String label) {
        return new Article(link, label, "test");
    }
}

class CabinetBuilder {

    private static List<Resource> oldArticles = new ArrayList<>();

    static ArticleCabinet cabinet() {
        oldValues();
        return new ArticleCabinet(oldArticles);
    }

    private static void oldValues() {
        oldArticles.clear();
        oldArticles.add(resource("A","A"));
        oldArticles.add(resource("B","B"));
        oldArticles.add(resource("C","C"));
        oldArticles.add(resource("D","D"));
        oldArticles.add(resource("E","E"));
        oldArticles.add(resource("F","F"));
    }

    static List<Resource> newArticles() {
        List<Resource> newArticles = new ArrayList<>();
        newArticles.add(resource("A","A"));
        newArticles.add(resource("B","B"));
        newArticles.add(resource("C","C"));
        newArticles.add(resource("D","D"));
        newArticles.add(resource("E","E"));
        newArticles.add(resource("F","F"));
        return newArticles;
    }

}