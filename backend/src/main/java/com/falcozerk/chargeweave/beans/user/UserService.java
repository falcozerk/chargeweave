package com.falcozerk.chargeweave.beans.user;

import com.falcozerk.chargeweave.auth.UserPrincipal;
import com.falcozerk.chargeweave.beans.charger.Charger;
import com.falcozerk.chargeweave.beans.common.CwBean;
import com.falcozerk.chargeweave.beans.common.CwService;
import com.falcozerk.chargeweave.integrations.google.ExcelImporter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class UserService extends CwService implements UserDetailsService {

    @Autowired
    UserRepository userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        User user = userRepo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
        );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow(
            () -> new RuntimeException("User not found: " + id)
        );

        return UserPrincipal.create(user);
    }

    public void importFrom(ExcelImporter pImporter, Workbook pWorkbook) {
        ArrayList<User> userList = new ArrayList<>();
        pImporter.importSheet( userList, pWorkbook, this, pImporter.COMPETITORS_TAB_POS );
        userRepo.saveAll( userList );
    }

    public CwBean createFrom(ExcelImporter pImporter, ArrayList<Cell> pRow ) {
        pImporter.reset();
        User user = new User();

        return user;
    }
}