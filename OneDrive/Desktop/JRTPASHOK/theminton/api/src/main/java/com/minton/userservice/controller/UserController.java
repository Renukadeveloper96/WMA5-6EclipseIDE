package com.minton.userservice.controller;

import com.minton.userservice.constants.EmailProperties;
import com.minton.userservice.entities.Role;
import com.minton.userservice.entities.User;
import com.minton.userservice.entities.UserType;
import com.minton.userservice.exception.ResourceNotFoundException;
import com.minton.userservice.payload.*;
import com.minton.userservice.repository.UserRepository;
import com.minton.userservice.security.CurrentUser;
import com.minton.userservice.security.UserPrincipal;
import com.minton.userservice.security.jwt.TokenProvider;
import com.minton.userservice.service.RoleService;
import com.minton.userservice.service.UserService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;
import  com.minton.userservice.repository.*;
@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
@Slf4j
@OpenAPIDefinition(info = @Info(title = "User Controller", version = "v3.3", description = "User Management Service"))
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private MessageSource messageSource;
    
    @GetMapping("/user/me")
   @PreAuthorize("hasAuthority('ROLE_USER')")
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
    public User getCurrentUser(@CurrentUser UserPrincipal currentUser) {

        User user = userService.getUserById(currentUser.getId());
        if (user != null) {
            return user;
        } else {
            throw new ResourceNotFoundException("User", "id", currentUser.getId());
        }

      }


    // Get all users
    @GetMapping("/user/getall")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users =userService.getAllUsers();
        if (users != null) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }


    // Get user by ID
    @GetMapping("/user/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }



    // Update an existing user
    @PutMapping("/user/update/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
    public ResponseEntity<User> updateUser(@PathVariable Long id,@Valid @RequestBody ModifyProfileRequest modifyProfileRequest) {
        User user = userService.getUserById(id);
        if (user != null) {

            User savedUser = userService.updateUser(user,modifyProfileRequest);

            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a user

    @DeleteMapping("/user/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            userService.deleteUser(user);
            return new ResponseEntity<>("User deleted success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/admin/role/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
    public ResponseEntity<Role> getAllRoles(@PathVariable Long id) {
        Role role =roleService.getRoleById(id);
        if (role != null) {
            return new ResponseEntity<>(role, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }



    @PostMapping("/admin/role/create")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
    public ResponseEntity<Role> createRole(@Valid @RequestBody RoleRequest roleRequest) {
        Role role=new Role();
        role.setName(roleRequest.getName());
        role.setDescription(roleRequest.getDescription());
        Role savedRole =roleService.create(role);
        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @DeleteMapping ("/admin/role/delete/{id}")
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
    public ResponseEntity<String> createRole(@PathVariable Long id) {

        Role role = roleService.getRoleById(id);
        if (role != null) {
            role.setActive(false);
            roleService.saveRole(role);
            return new ResponseEntity<>("Role deleted success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/admin/role/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
    public ResponseEntity<Role> updateRole(@PathVariable Long id,@Valid @RequestBody RoleRequest roleRequest) {
        Role role=roleService.getRoleById(id);

        if (role != null) {
            role.setName(roleRequest.getName());
            Role savedRole = roleService.create(role);
            return new ResponseEntity<>(savedRole, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/admin/role/getall")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
    public ResponseEntity<List<Role>> getAllRole() {
        List<Role> roles=roleService.getAllRoles();
        if (roles != null) {
            return new ResponseEntity<>(roles, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/admin/usertype/create")
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
    public ResponseEntity<UserType> createUserType(@Valid @RequestBody UserTypeRequest userTypeRequest) {
        UserType userType=new UserType();
        userType.setName(userTypeRequest.getName());
        UserType savedUserType =roleService.saveUserType(userType);
        return new ResponseEntity<>(savedUserType, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @DeleteMapping ("/admin/usertype/delete/{id}")
    public ResponseEntity<String> deleteUserType(@PathVariable Long id) {

        UserType userType = roleService.getUserType(id);
        if (userType != null) {
            userType.setDeletedOn(new Date());
            userType.setDeleteFlag(true);
            roleService.saveUserType(userType);
            return new ResponseEntity<>("UserType deleted success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/admin/usertype/getall")
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
    public ResponseEntity<List<UserType>> getAllUserType() {
        List<UserType> userTypes=roleService.getAllUserTypes();
        if (userTypes != null) {
            return new ResponseEntity<>(userTypes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    //this api will send  "forget password link" in email
    @GetMapping("/auth/forgetpassword/sendlink/{email}")
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
    public ResponseEntity<ApiResponse> forgetPassword(@PathVariable String  email)
            throws MessagingException, IOException, TemplateException
        {
        log.info("/forgetPassword" + email);
       User user=userService.getUserByEmail(email.strip());

        if (user != null) {
            sentResetPasswordEmail(user);
            String msg="password reset link send to "+email;
             return  ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,msg));
        } else {
            String msg="This email is not registered in our website";
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse(true,msg));
          }

    }

    /**
     * this api will accept token from UI and new password
     * @param resetPassword
     * @return
     */
    @PostMapping("/auth/resetpassword")
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
    public ResponseEntity<?> restPassword(@RequestBody ResetNewPasswordRequest resetPassword) {

        //validate token,
        String token=resetPassword.getToken();
        if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
            Long userId = tokenProvider.getUserIdFromToken(token);
            User user = userService.getUserById(userId);
            user.setPassword(passwordEncoder.encode(resetPassword.getPassword()));
            User result = userRepository.save(user);
            return  ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,"User password updated!"));

        }
        else
            return new ResponseEntity<>("Invalid token or token is expired",HttpStatus.OK);


    }

    @PostMapping("/user/resetpassword")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
    public ResponseEntity<?> authenticateUserForm(@RequestBody ResetPasswordRequest resetPasswordRequestdto){

        User user =userService.getUserByEmail(resetPasswordRequestdto.getEmail());
        if(user!=null)
        {
            user.setPassword(passwordEncoder.encode(resetPasswordRequestdto.getNewPassword()));

            User result = userRepository.save(user);
            return  ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,"User password updated!!"));

        }
        else
        {
            throw new ResourceNotFoundException("User","email",resetPasswordRequestdto);
        }
    }

    @Async
    public void sentResetPasswordEmail( User user) throws MessagingException, IOException, TemplateException {

        String code=tokenProvider.getPasswordResetJWT(user);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(emailProperties.getUsername());
        helper.setTo(user.getEmail());
        String subjectLine= messageSource.getMessage( "forgot_password_email_subjet", null, Locale.ENGLISH );
        helper.setSubject(subjectLine);
        Map<String, Object> model = new HashMap<>(16);
        model.put("code", code);
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
        cfg.setClassForTemplateLoading(this.getClass(), "/templates");
        Template template = cfg.getTemplate("resetPasswordCodeEmail.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        helper.setText(html, true);

        javaMailSender.send(mimeMessage);


    }

}
