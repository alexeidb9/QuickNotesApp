package controller;

import com.quicknotes.controller.UserController;
import com.quicknotes.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// TODO: view_ReturnsViewPageWithUserFromDatabase_WhenUserIdExistsInDatabase()
// TODO: view_ReturnsViewPageWithEmpty_WhenUserIdDoesNotExist()
// TODO: add_ReturnsViewPageWithEmptyUser()
// TODO: edit_ReturnsEditView_WhenUserEditViewIsAccessedAndUserExists()
// TODO: edit_ReturnsEditView_WhenUserEditViewIsAccessedAndUserDoesNotExists()
// TODO: delete_ReturnsDeleteView_WhenUserDeleteViewIsAccessedAndUserExists()
// TODO: delete_ReturnsDeleteView_WhenUserDeleteViewIsAccessedAndUserDoesNotExists()
// TODO: save_SavesUserRecord_WhenUserRecordIsValid()
// TODO: deletion_DeletesUserRecord_WhenUserRecordIsValid()

@ExtendWith(SpringExtension.class)
public class UserControllerTest {


    private MockMvc mvc;

    @MockBean
    private UserService service;

    private UserController fixture;

    @BeforeEach
    public void setUp() {
        fixture = new UserController(service);
        this.mvc = MockMvcBuilders.standaloneSetup(fixture).build();
    }



    @Test
    public void index_RedirectsToListView_WhenUserHomeIsAccessed() throws Exception {
        // @formatter:off
        mvc.perform(
                get("/users/")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));
        // @formatter: on

        then(service).shouldHaveNoMoreInteractions();
    }

//    @Test
//    public void list_ReturnsViewWithRecords_WhenUserListViewIsAccessed() throws Exception {
//        final int pageNumber = 0;
//        final int pageSize = DEFAULT_PAGE_SIZE;
//        final int totalPages = (int) (Math.random() * 100);
//
//        final User user1 = new User (UUID.randomUUID(), UUID.randomUUID().toString(), UUID.randomUUID().toString());
//        final User user2 = new User (UUID.randomUUID(), UUID.randomUUID().toString(), UUID.randomUUID().toString());
//
//        final List<User> users = Arrays.asList(user1, user2);
//        final Pageable page = PageRequest.of(pageNumber, pageSize);
//
//        final Page<User> response = new PageImpl<>(users,page,totalPages);
//
//        given(service.getUsers(pageNumber,pageSize)).willReturn(response);
//
//        //@formatter: off
//        mvc.perform(
//                get("/users/list")
//                .param("page",String.valueOf(pageNumber))
//                .param("size", String.valueOf(pageSize))
//        )
//                .andExpect((status().isOk()))
//                .andExpect(model().attribute("users",hasItems(user1, user2)))
//                .andExpect(view().name("users/list"));
//        //@formatter: on
//
//        then(service).should().getUser(pageNumber,pageSize);
//        then(service).shouldHaveNoMoreInteractions();
//
//
//    }

//    @Test
//    public void list_ReturnsViewForFirstPage_WhenUserListViewIsAccessed() throws Exception {
//
//        final int pageNumber = 0;
//        final int pageSize = DEFAULT_PAGE_SIZE;
//        final int totalPages = (int) (Math.random() * 100);
//
//        final User user = new User(UUID.randomUUID(),UUID.randomUUID().toString(),UUID.randomUUID().toString());
//        final Pageable page = PageRequest.of(pageNumber, pageSize);
//
//        final Page<User> response = new PageImpl<>(Collections.singletonList(user),page,totalPages);
//
//        given(service.getUser(pageNumber,pageSize)).willReturn(response);
//
//        // @formatter: off
//        mvc.perform(
//                get("/users/list")
//                .param("page", String.valueOf(pageNumber))
//                .param("size",String.valueOf(pageSize))
//        )
//                .andExpect(status().isOk())
//                .andExpect(model().attribute("previousPageNumber", is(-1)))
//                .andExpect(model().attribute("nextPageNumber", is(-1)))
//                .andExpect(view().name("/users/list"));
//
//        // @formatter: on
//
//        then(service).should().getUsers(pageNumber,pageSize);
//        then(service).shouldHaveNoMoreInteractions();
//
//
//    }

//    @Test
//    public void list_ReturnsViewForLastPage_WhenUserListViewIsAccessed() throws Exception {
//
//        final int pageNumber = 0;
//        final int pageSize = DEFAULT_PAGE_SIZE;
//        final int totalPages = DEFAULT_PAGE_SIZE;
//
//        final User user = new User(UUID.randomUUID(),UUID.randomUUID().toString(),UUID.randomUUID().toString());
//        final Pageable page = PageRequest.of(pageNumber, pageSize);
//
//        final Page<User> response = new PageImpl<>(Collections.singletonList(user),page,totalPages);
//
//        given(service.getUser(pageNumber,pageSize)).willReturn(response);
//
//
//        // @formatter: off
//        mvc.perform(
//                get("/users/list")
//                        .param("page", String.valueOf(pageNumber))
//                        .param("size",String.valueOf(pageSize))
//        )
//                .andExpect(status().isOk())
//                .andExpect(model().attribute("previousPageNumber", is(0)))
//                .andExpect(model().attribute("nextPageNumber", is(-1)))
//                .andExpect(view().name("/users/list"));
//
//        // @formatter: on
//
//        then(service).should().getUsers(pageNumber,pageSize);
//        then(service).shouldHaveNoMoreInteractions();
//
//
//
//    }


}
