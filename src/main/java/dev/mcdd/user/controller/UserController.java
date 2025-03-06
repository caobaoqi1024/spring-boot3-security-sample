package dev.mcdd.user.controller;

import dev.mcdd.user.entity.Permission;
import dev.mcdd.user.entity.User;
import dev.mcdd.user.service.PermissionService;
import dev.mcdd.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PermissionService permissionService;

    @GetMapping("/{id}")
    ResponseEntity<User> findById(@PathVariable Long id) {
        log.info("UserController.findById id = {}", id);
        User user = userService.findUserByUserId(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    ResponseEntity<List<Permission>> list(){
        return ResponseEntity.ok(permissionService.list());
    }

}
