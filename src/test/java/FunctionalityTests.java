import com.quicknotes.entity.Note;
import com.quicknotes.entity.QuickNote;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FunctionalityTests {

    @Test
    public void noteId_IsUnique_thenCorrect(){
        Note note1 = new QuickNote();
        Note note2 = new QuickNote();


        assertThat(note1.getId()).isNotEqualTo(note2.getId());

    }

}
