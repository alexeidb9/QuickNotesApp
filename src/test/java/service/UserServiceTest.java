package service;

import com.quicknotes.entity.User;
import com.quicknotes.repository.UserRepository;
import com.quicknotes.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @MockBean
    private UserRepository repository;

    private UserService fixture;


    @BeforeEach
    public void setUp() {
        fixture = new UserService(repository);
    }


    @Test
    public void getUsers_ReturnsUsers_WhenUsersExists() {

        final int pageNumber = (int) (Math.random() * 100);
        final int pageSize = (int) (Math.random() * 100);

        final int totalRecords = (int) (Math.random() * 100);

        final User user1 = new User(UUID.randomUUID(), UUID.randomUUID().toString(), UUID.randomUUID().toString());
        final User user2 = new User(UUID.randomUUID(), UUID.randomUUID().toString(), UUID.randomUUID().toString());
        final User user3 = new User(UUID.randomUUID(), UUID.randomUUID().toString(), UUID.randomUUID().toString());

        final List<User> users = Arrays.asList(user1, user2, user3);

        final PageRequest page = PageRequest.of(pageNumber, pageSize);

        final Page<User> expected = new PageImpl<>(users, page, totalRecords);

        given(repository.findAll(page)).willReturn(expected);

        final Page<User> actual = fixture.getUsers(pageNumber, pageSize);

        assertThat(actual).isEqualTo(expected);
        assertThat(actual.getContent()).hasSameElementsAs(users);
        assertThat(actual.getPageable()).isEqualTo(page);

        then(repository).should().findAll(page);
        then(repository).shouldHaveNoMoreInteractions();

    }


    @Test
    public void getUser_ReturnsUser_WhenUserExist() {

        final UUID id = UUID.randomUUID();

        final User user = new User(UUID.randomUUID(), UUID.randomUUID().toString(), UUID.randomUUID().toString());

        final Optional<User> expected = Optional.of(user);

        given(repository.findById(id)).willReturn(expected);

        final Optional<User> actual = fixture.getUser(id);

        assertThat(actual).isEqualTo(expected);

        then(repository).should().findById(id);
        then(repository).shouldHaveNoMoreInteractions();

    }


    @Test
    public void getUser_ReturnsUser_WhenUserDoesNotExist() {

        final UUID id = UUID.randomUUID();

        final Optional<User> expected = Optional.empty();

        given(repository.findById(id)).willReturn(expected);

        final Optional<User> actual = fixture.getUser(id);

        assertThat(actual).isEqualTo(expected);

        then(repository).should().findById(id);
        then(repository).shouldHaveNoMoreInteractions();


    }

    @Test
    public void save_ReturnSaved_WhenUserRecordIsCreated() {

        final UUID id = UUID.randomUUID();

        final User expected = new User();
        expected.setUsername(UUID.randomUUID().toString());
        expected.setPassword(UUID.randomUUID().toString());

        given(repository.save(expected)).willAnswer(invocation -> {

            final User toSave = invocation.getArgument(0);

            toSave.setId(id);
            return toSave;
        });

        final User actual = fixture.save(expected);

        assertThat(actual).isEqualTo(expected);

        then(repository).should().save(expected);
        then(repository).shouldHaveNoMoreInteractions();

    }

    @Test
    public void delete_DeletesUser_WhenUserExists() {

        final UUID id = UUID.randomUUID();

        willDoNothing().given(repository).deleteById(id);

        fixture.delete(id);

        then(repository).should().deleteById(id);
        then(repository).shouldHaveNoMoreInteractions();


    }


}
