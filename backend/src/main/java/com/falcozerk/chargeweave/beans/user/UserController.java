package com.falcozerk.chargeweave.beans.user;

import com.falcozerk.chargeweave.beans.charger.Charger;
import com.falcozerk.chargeweave.beans.charger.ChargerRepository;
import com.falcozerk.chargeweave.beans.common.CwController;
import com.falcozerk.chargeweave.util.PagedResponse;
import com.falcozerk.chargeweave.auth.CurrentUser;
import com.falcozerk.chargeweave.auth.UserPrincipal;
import com.falcozerk.chargeweave.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController extends CwController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found:" + username));

        return new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt());
    }

    @GetMapping("/users/{username}/visits")
    public PagedResponse<Charger> getChargersVisitedBy(@PathVariable(value = "username") String username,
                                                       @CurrentUser UserPrincipal currentUser,
                                                       @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                       @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return null; // chargerService.getChargersVisitedBy(username, currentUser, page, size);
    }

}
