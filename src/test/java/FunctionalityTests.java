import com.quicknotes.entity.Note;
import com.quicknotes.entity.QuickNote;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalityTests {

    @Test
    public void noteId_IsUnique_thenCorrect(){
        Note note1 = new QuickNote();
        Note note2 = new QuickNote();
        Assert.assertNotEquals(note1.getId(),note2.getId());

    }

}
