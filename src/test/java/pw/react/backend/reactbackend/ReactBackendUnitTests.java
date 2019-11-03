package pw.react.backend.reactbackend;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import pw.react.backend.reactbackend.exception.ResourceNotFoundException;
import pw.react.backend.reactbackend.exception.UserAlreadyExistsException;
import pw.react.backend.reactbackend.user.User;
import pw.react.backend.reactbackend.user.UserController;
import pw.react.backend.reactbackend.user.UserRepository;
import pw.react.backend.reactbackend.user.service.UserServiceImpl;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ReactBackendUnitTests {

    private UserServiceImpl userService;
    private UserController userController;

    @Mock
    private UserRepository userRepository;


    @Before
    public void setUp() throws Exception {
        userService = spy(new UserServiceImpl(userRepository));
        userController = new UserController(userRepository, userService);
    }



    @Test
    public void givenNotExistingUser_whenCheckIfUserExists_thenReturnTrue() {
        User user = new User();
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assert !userService.userExists(user);
    }

    @Test
    public void givenExistingUser_whenCheckIfUserExists_thenReturnFalse() {
        User user = new User();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        assert userService.userExists(user);
    }

    @Test
    public void givenNotExistingUserWithoutLoginSet_whenCheckIfLoginExists_thenThrowResourceNotFoundException() {
        User user = new User();
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        try {
            userService.loginExists(user.getLogin());
            fail("Should throw ResourceNotFoundException");
        } catch (ResourceNotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("User with login [null] not found.")));
        }
    }

    @Test
    public void givenNotExistingUserWithLoginSet_whenCheckIfLoginExists_thenThrowResourceNotFoundException() {
        User user = new User();
        user.setLogin("blabla");
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        try {
            userService.loginExists(user.getLogin());
            fail("Should throw ResourceNotFoundException");
        } catch (ResourceNotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("User with login [blabla] not found.")));
        }
    }

    @Test
    public void givenNotExistingUser_whenCreateUser_thenExecuteSaveMethod() {
        User user = new User();

        userController.createUser(user);

        verify(userRepository, times(1)).save(eq(user));
    }

    @Test
    public void giveExistingUser_whenCreateUser_thenExecuteSaveMethod() {
        User user = new User();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        try {
            userController.createUser(user);
            fail("Should throw UserAlreadyExistsException");
        } catch (UserAlreadyExistsException ex) {
            assertThat(ex.getMessage(), is(equalTo("User with id [0] already exists.")));
        }
    }

    @Test
    public void givenNotExistingUser_whenGetUserById_thenThrowResourceNotFoundException() {
        User user = new User();

        try {
            userController.getUserById(user.getId());
            fail("Should throw ResourceNotFoundException");
        } catch (ResourceNotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("User with id [0] not found.")));
        }
    }

    @Test
    public void givenExistingUser_whenGetUserById_thenExecuteFindByIdMethod() {
        User user = new User();
        user.setId(1);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        userController.getUserById(user.getId());

        verify(userRepository, times(1)).findById(eq(user.getId()));
    }

    @Test
    public void givenNotExistingUser_whenUpdateUser_thenThrowResourceNotFoundException() {
        User user = new User();

        try {
            userController.updateUser(392l, user);
            fail("Should throw ResourceNotFoundException");
        } catch (ResourceNotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("User with id [392] not found.")));
        }
    }

    @Test
    public void givenExistingUser_whenUpdateUser_thenExecuteSaveMethod() {
        User user = new User();
        user.setId(1);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        userController.updateUser(1l, user);

        verify(userRepository, times(1)).save(eq(user));
    }

    @Test
    public void givenNotExistingUser_whenDeleteUser_thenThrowResourceNotFoundException() {
        User user = new User();
        user.setId(123);

        try {
            userController.deleteUser(user.getId());
            fail("Should throw ResourceNotFoundException");
        } catch (ResourceNotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("User with id [123] not found.")));
        }
    }

    @Test
    public void givenExistingUser_whenDeleteUser_thenExecuteDeleteMethod() {
        User user = new User();
        user.setId(1);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        userController.deleteUser(1l);

        verify(userRepository, times(1)).delete(eq(user));

    }

    @Test
    public void givenExistingUser_whenDeleteNotExistingUser_thenThrowResourceNotFoundException() {
        User user = new User();
        user.setId(1);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        try {
            userController.deleteUser(2l);
            fail("Should throw ResourceNotFoundException");
        } catch (ResourceNotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("User with id [2] not found.")));
        }

    }
}
